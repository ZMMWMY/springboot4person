package com.mrz.secKill.model;

import lombok.Data;

import java.util.Date;

/**
 * Author : MrZ
 *
 * @Description Created in 15:08 on 2017/5/31.
 * Modified By :
 */
@Data
public class Order {

    private Integer id;

    private String goodUrl;

    private String mobile;

    private Date createTime;

    private Integer enable;
}
