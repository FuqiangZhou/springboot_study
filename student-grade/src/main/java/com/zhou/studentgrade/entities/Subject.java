package com.zhou.studentgrade.entities;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2020/3/10 4:18 下午
 */
@Data
public class Subject implements Serializable {

    private String subId;

    private String subName;
}
