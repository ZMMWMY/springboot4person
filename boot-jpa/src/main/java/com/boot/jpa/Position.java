package com.boot.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Author : MrZ
 *
 * @Description Created in 14:54 on 2017/6/16.
 * Modified By :
 */
@Entity
@Table(name = "position")
public class Position {

    @Id
    private Integer id;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
