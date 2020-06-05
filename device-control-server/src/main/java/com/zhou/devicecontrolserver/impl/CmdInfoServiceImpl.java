package com.zhou.devicecontrolserver.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.devicecontrolserver.entity.CmdInfo;
import com.zhou.devicecontrolserver.mapper.CmdInfoMapper;
import com.zhou.devicecontrolserver.service.CmdInfoService;
import org.springframework.stereotype.Service;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/11/27 1:34 下午
 */
@Service
public class CmdInfoServiceImpl extends ServiceImpl<CmdInfoMapper, CmdInfo> implements CmdInfoService {
}
