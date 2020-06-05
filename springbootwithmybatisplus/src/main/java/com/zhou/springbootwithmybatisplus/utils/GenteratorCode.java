package com.zhou.springbootwithmybatisplus.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Scanner;


/***
 * @author 周继文
 * @date 2019/7/24 15:32
 * @version 1.0.0
 */
public class GenteratorCode {
    public static void main(String[] args) throws InterruptedException {
        //用来获取mybatis-plus.properties文件的配置信息
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();

        Scanner scanner = new Scanner(System.in);

//        System.out.println("请输入生成文件存储路径:");
//        String path = scanner.next();
//        if ( StringUtils.isBlank(path)){
//            path = "/Users/zhoufuqiang";
//        }
//        System.out.println("=====>文件路径: " + path);
        gc.setOutputDir("/Users/zhoufuqiang/源码");
        gc.setFileOverride(true);
        gc.setSwagger2(false);
        gc.setActiveRecord(false);
        // XML 二级缓存
        gc.setEnableCache(false);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columList
        gc.setBaseColumnList(true);
        gc.setAuthor("51quicker");
        //主键策略
        gc.setIdType(IdType.ID_WORKER_STR);
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new MySqlTypeConvert() {
            @Override
            public DbColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                if (fieldType.contains("date")){
                    return DbColumnType.DATE;
                }
                return (DbColumnType) super.processTypeConvert(globalConfig, fieldType);
            }
        });
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
//        dsc.setUrl("jdbc:mysql://mysql.51quicker.com:3306/zhongliang");
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/sana");
//        dsc.setUsername("quicker");
        dsc.setUsername("root");
//        dsc.setPassword("quicker2018@)!*");
        dsc.setPassword("admin123");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 驼峰转连字符 如 umps_user 变为 upms/user
        strategy.setControllerMappingHyphenStyle(true);

//        System.out.println("请输入需要生成的表, 用\",\"隔开");
//        String tables = scanner.next();
//        String[] tableArr = tables.split(",");
        String tables = "ord_commodity,learn_versions_volume";

        // 需要生成的表
        strategy.setInclude(tables.split(","));
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
//        System.out.println("请输入包路径:");
//        String pkg = scanner.next();
        pc.setParent("com.zhou.easyexcel");
        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("impl");
        pc.setEntity("entity");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        mpg.execute();
    }
}

