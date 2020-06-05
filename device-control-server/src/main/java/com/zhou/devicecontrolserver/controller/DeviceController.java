package com.zhou.devicecontrolserver.controller;
import java.io.*;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhou.devicecontrolserver.entity.CmdInfo;
import com.zhou.devicecontrolserver.entity.DeviceInfo;
import com.zhou.devicecontrolserver.entity.DeviceRecord;
import com.zhou.devicecontrolserver.service.CmdInfoService;
import com.zhou.devicecontrolserver.service.DeviceInfoService;
import com.zhou.devicecontrolserver.service.DeviceRecordService;
import com.zhou.devicecontrolserver.utils.HttpUtils;
import com.zhou.devicecontrolserver.utils.IDUtils;
import com.zhou.devicecontrolserver.utils.PageDataResult;
import com.zhou.devicecontrolserver.vo.CmdExecResVO;
import com.zhou.devicecontrolserver.vo.DeviceInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/11/27 1:45 下午
 */
@RestController
@RequestMapping("/deviceCtl")
public class DeviceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceController.class);

    private static final String EXECUTE_URL = "http://192.168.0.103:8888/";

    @Resource
    private CmdInfoService cmdInfoService;

    @Resource
    private DeviceInfoService deviceInfoService;

    @Resource
    private DeviceRecordService deviceRecordService;

    /**
     * 功能描述: 发送指令
     * @author 周富强
     * @date 2019/11/27 1:58 下午
     * @param deviceId 设备ID
     * @param devicePort 设备端口
     * @param cmd 指令(1,开灯; 2,关灯)
     * @return
     */
    @GetMapping("/sendCmd")
    public PageDataResult<String> sendCmd(@RequestParam(value = "deviceId") Integer deviceId,
                                  @RequestParam(value = "devicePort") Integer devicePort,
                                  @RequestParam(value = "cmd") Integer cmd){
        try {
            Map<String, Object> executeCmdMap = new HashMap<>();
            String id = IDUtils.getId();
            CmdInfo cmdInfo = new CmdInfo();
            cmdInfo.setId(id);
            cmdInfo.setDeviceId(deviceId);
            cmdInfo.setDevicePort(devicePort);
            cmdInfo.setExecuteCmd(cmd);
            cmdInfo.setCreateTime(new Date());
            this.cmdInfoService.save(cmdInfo);
            executeCmdMap.put("id", id);
            executeCmdMap.put("deviceId", deviceId);
            executeCmdMap.put("devicePort", devicePort);
            executeCmdMap.put("cmd", cmd);
            String res = HttpUtils.sendPost(EXECUTE_URL, JSON.toJSONString(executeCmdMap));
            return new PageDataResult<>(1, res, "操作成功");
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return new PageDataResult<>(2, "系统错误");
        }
    }

    @GetMapping("/cmdInfoList")
    public PageDataResult<List<CmdInfo>> cmdInfoList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                     @RequestParam(value = "limit", defaultValue = "10") Integer limit){
        try {
            IPage<CmdInfo> cmdInfoIPage = this.cmdInfoService.page(new Page<>(page, limit), new QueryWrapper<CmdInfo>().orderByDesc("res_time"));
            return new PageDataResult<>(cmdInfoIPage.getTotal(), 1, cmdInfoIPage.getRecords(), "操作成功");
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return new PageDataResult<>(2, "系统错误");
        }
    }

    @GetMapping("/deviceInfoList")
    public PageDataResult<List<DeviceInfo>> deviceInfoList(){
        try {
            List<DeviceInfo> deviceInfos = this.deviceInfoService.list();
            return new PageDataResult<>(1, deviceInfos, "操作成功");
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return new PageDataResult<>(2, "系统错误");
        }
    }

    @GetMapping("/executeCmdCount")
    public PageDataResult<List<Map<String, Object>>> executeCmdCount(){
        try {
            int executeSuccessCount = this.cmdInfoService.count(new QueryWrapper<CmdInfo>().eq("execute_res", 1));
            int executeFailedCount = this.cmdInfoService.count(new QueryWrapper<CmdInfo>().eq("execute_res", 0));
            List<Map<String, Object>> resList = new ArrayList<>();
            Map<String, Object> success = new HashMap<>();
            success.put("name", "执行成功");
            success.put("value", executeSuccessCount);
            Map<String, Object> failed = new HashMap<>();
            failed.put("name", "执行失败");
            failed.put("value", executeFailedCount);
            resList.add(success);
            resList.add(failed);
            return new PageDataResult<>(1, resList, "操作成功");
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return new PageDataResult<>(2, "系统错误");
        }
    }

    /**
     * 功能描述: 设备状态回执接口
     * @author 周富强
     * @date 2019/11/27 2:38 下午
     * @return
     */
    @PostMapping("/deviceStatusCallback")
    public void deviceStatusCallback(HttpServletRequest request, HttpServletResponse response){
        BufferedReader br = null;
        OutputStream out = null;
        try {
            br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            StringBuilder resBuild = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null){
                resBuild.append(line);
            }
            LOGGER.info("request data is {}", resBuild.toString());
            out = new BufferedOutputStream(response.getOutputStream());
            Map<String, Object> respMap = new HashMap<>();
            respMap.put("code", 200);
            respMap.put("message", "SUCCESS");
            out.write(JSON.toJSONString(respMap).getBytes());
            out.flush();
            //更新设备状态
            DeviceInfoVO deviceInfoVO = JSON.parseObject(resBuild.toString(), new TypeReference<DeviceInfoVO>() {});
            DeviceInfo deviceInfo = new DeviceInfo();
            deviceInfo.setDeviceId(deviceInfoVO.getDeviceId());
            deviceInfo.setStatus(deviceInfoVO.getStatus());
            deviceInfo.setUpdateTime(new Date());
            this.deviceInfoService.updateById(deviceInfo);
            //记录设备上下线信息
            DeviceRecord deviceRecord = new DeviceRecord();
            deviceRecord.setId(IDUtils.getId());
            deviceRecord.setDeviceId(deviceInfoVO.getDeviceId());
            deviceRecord.setRecordType(deviceInfoVO.getStatus());
            deviceRecord.setRecordTime(new Date());
            this.deviceRecordService.save(deviceRecord);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (out != null){
                    out.close();
                }
                if (br != null){
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 功能描述: 指令执行回执接口
     * @author 周富强
     * @date 2019/11/27 2:38 下午
     * @return
     */
    @PostMapping("/cmdExecResCallback")
    public void cmdExecResCallback(HttpServletRequest request, HttpServletResponse response){
        BufferedReader br = null;
        OutputStream out = null;
        try {
            br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            StringBuilder resBuild = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null){
                resBuild.append(line);
            }
            LOGGER.info("request data is {}", resBuild.toString());
            out = new BufferedOutputStream(response.getOutputStream());
            Map<String, Object> respMap = new HashMap<>();
            respMap.put("code", 200);
            respMap.put("message", "SUCCESS");
            out.write(JSON.toJSONString(respMap).getBytes());
            out.flush();
            CmdExecResVO cmdExecResVO = JSON.parseObject(resBuild.toString(), new TypeReference<CmdExecResVO>() {});
            CmdInfo cmdInfo = this.cmdInfoService.getById(cmdExecResVO.getId());
            cmdInfo.setExecuteRes(cmdExecResVO.getRes() == 1 ? 1 : 0);
            cmdInfo.setRetryNum(cmdExecResVO.getRetryNum() == null ? 3 : cmdExecResVO.getRetryNum());
            cmdInfo.setResTime(new Date());
            this.cmdInfoService.updateById(cmdInfo);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (out != null){
                    out.close();
                }
                if (br != null){
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
