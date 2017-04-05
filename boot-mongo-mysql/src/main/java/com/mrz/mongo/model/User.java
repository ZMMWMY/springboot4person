package com.mrz.mongo.model;

import org.springframework.data.annotation.Id;

/**
 * Author : MrZ
 *
 * @Description Created in 11:36 on 2017/3/30.
 * Modified By :
 */
public class User {


    /**
     * 。如果主键字段命名ID 使用@ ID注释不是强制性的。
     * 然而，许多人仍然使用注释的可读性。有些人认为这是最好的做法，使用ID注释，即使当它不是必需的。
     * */
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
