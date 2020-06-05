package com.zhou.dynamicdatasource.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhou.dynamicdatasource.entity.Banner;

import java.util.List;

public interface BannerService extends IService<Banner> {

    List<Banner> getBanners(String dataSourceName);

}