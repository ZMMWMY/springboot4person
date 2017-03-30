package com.mrz.mongo.model;

import org.springframework.data.annotation.Id;

/**
 * Author : MrZ
 *
 * @Description Created in 11:36 on 2017/3/30.
 * Modified By :
 */
public class User {

    @Id
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
