package com.zhou.dynamicdatasource.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.dynamicdatasource.entity.Banner;
import com.zhou.dynamicdatasource.mapper.BannerMapper;
import com.zhou.dynamicdatasource.service.BannerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {


    @Override
    @DS("#dataSourceName")
    public List<Banner> getBanners(String dataSourceName) {
        return this.baseMapper.selectList(new QueryWrapper<>());
    }
}