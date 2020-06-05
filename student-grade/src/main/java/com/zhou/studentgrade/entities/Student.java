package com.zhou.studentgrade.entities;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2020/3/10 4:12 下午
 */
@Data
public class Student implements Serializable {

    private String stuId;

    private String stuName;

    private Integer stuAge;

    private String stuAddress;
}
