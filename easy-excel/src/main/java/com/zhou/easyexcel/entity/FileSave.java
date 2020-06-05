package com.zhou.easyexcel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/10/10 5:38 下午
 */
@Data
@TableName("file_save")
public class FileSave {


    /**
     * 文件code
     */
    @TableId("file_code")
    private String fileCode;

    /**
     * 文件名
     */
    @TableField("file_name")
    private String fileName;

    /**
     * 创建日期
     */
    @TableField("create_date")
    private Date createDate;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
}
