package com.zhou.easyexcel.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/10/10 5:14 下午
 */
@Data
public class StoredValueCardVO {

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
     * 余额
     */
    @ExcelProperty("余额")
    private Double accountBalance;
}
