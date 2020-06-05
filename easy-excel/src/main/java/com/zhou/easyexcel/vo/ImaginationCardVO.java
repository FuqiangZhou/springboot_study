package com.zhou.easyexcel.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/10/10 6:57 下午
 */
@Data
public class ImaginationCardVO {

    /**
     * 昵称
     */
    @ExcelProperty("昵称")
    private String nickname;

    /**
     * 联系电话
     */
    @ExcelProperty("联系电话")
    private String userPhone;

    /**
     * 问卷项
     */
    @ExcelProperty("问卷项")
    private String questionnaireItem;

    /**
     * 到期时间
     */
    @ExcelProperty("到期时间")
    private Date validityPeriod;

    /**
     * 剩余天数
     */
    @ExcelProperty("剩余天数")
    private Long remainingDays;
}
