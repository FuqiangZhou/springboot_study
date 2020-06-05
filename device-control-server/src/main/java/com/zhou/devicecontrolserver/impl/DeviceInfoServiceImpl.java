package com.zhou.devicecontrolserver.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.devicecontrolserver.entity.DeviceInfo;
import com.zhou.devicecontrolserver.mapper.DeviceInfoMapper;
import com.zhou.devicecontrolserver.service.DeviceInfoService;
import org.springframework.stereotype.Service;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/11/27 1:37 下午
 */
@Service
public class DeviceInfoServiceImpl extends ServiceImpl<DeviceInfoMapper, DeviceInfo> implements DeviceInfoService {
}
