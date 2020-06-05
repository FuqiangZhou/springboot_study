package com.zhou.dynamicdatasource.controller;

import com.zhou.dynamicdatasource.entity.Banner;
import com.zhou.dynamicdatasource.service.BannerService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/10/30 2:06 下午
 */
@RestController
@RequestMapping("/banner")
public class BannerController {

    @Resource
    private BannerService bannerService;


    @GetMapping("/listBanner")
    public List<Banner> listBanner(@RequestParam(value = "dataSourceName") String dataSourceName){
        return this.bannerService.getBanners(dataSourceName);
    }




}
