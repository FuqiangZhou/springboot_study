package com.zhou.devicecontrolserver.job;

import com.alibaba.fastjson.JSON;
import com.zhou.devicecontrolserver.entity.CmdInfo;
import com.zhou.devicecontrolserver.service.CmdInfoService;
import com.zhou.devicecontrolserver.utils.HttpUtils;
import com.zhou.devicecontrolserver.utils.IDUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/12/3 10:48 上午
 */
public class MarqueeJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(MarqueeJob.class);

    private static final String EXECUTE_URL = "http://127.0.0.1:8888/";

    @Resource
    private CmdInfoService cmdInfoService;

    private static int flag = 0;

    private static boolean close = false;

    private static int openDeviceId = 0;

    private static int closeDeviceId = 0;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        if (!close){
            flag ++;
            sendCmd(openDeviceId, flag, 1);
            if (flag >= 20){
                close = true;
                flag = 0;
                openDeviceId ++;
                //跳过模组4
                if (openDeviceId == 4){
                    openDeviceId ++;
                }else if (openDeviceId > 7){
                    //当第模组七指令执行结束后重新回到模组0
                    openDeviceId = 0;
                }
            }
        }else {
            flag ++;
            sendCmd(closeDeviceId, flag, 0);
            if (flag >= 20){
                close = false;
                flag = 0;
                closeDeviceId ++;
                //跳过模组4
                if (closeDeviceId == 4){
                    closeDeviceId ++;
                }else if (closeDeviceId > 7){
                    //当第模组七指令执行结束后重新回到模组0
                    closeDeviceId = 0;
                }
            }
        }
    }

    private void sendCmd(Integer deviceId, Integer devicePort, Integer cmd){
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
        LOGGER.info("返回结果为=>{}", res);
    }


}
