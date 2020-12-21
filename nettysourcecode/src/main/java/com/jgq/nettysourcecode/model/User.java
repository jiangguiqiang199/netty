package com.jgq.nettysourcecode.model;

import lombok.Data;

/**
 * @Author: Created by carrot
 * 2020/11/20 19:35
 */
@Data
public class User {

    public User(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    private Integer age;

    private String name;
}
