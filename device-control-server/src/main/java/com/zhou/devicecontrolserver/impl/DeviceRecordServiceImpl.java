package com.zhou.devicecontrolserver.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.devicecontrolserver.entity.DeviceRecord;
import com.zhou.devicecontrolserver.mapper.DeviceRecordMapper;
import com.zhou.devicecontrolserver.service.DeviceRecordService;
import org.springframework.stereotype.Service;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/12/2 10:16 上午
 */
@Service
public class DeviceRecordServiceImpl extends ServiceImpl<DeviceRecordMapper, DeviceRecord> implements DeviceRecordService {
}
