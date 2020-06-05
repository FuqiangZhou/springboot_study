package com.zhou.springbootwithmybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019-06-02 15:10
 */
@Data
@Builder
@TableName("t_person")
public class Person {

    private Long id;

    private String name;

    private Integer age;

    private String address;
}
