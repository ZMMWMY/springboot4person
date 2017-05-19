package com.mrz.secKill.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Author : MrZ
 *
 * @Description Created in 13:49 on 2017/5/19.
 * Modified By :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Good {
    private Integer id;

    private String name;

    private Integer stock;

    private String link;

    private Date startTime;

    private Date endTime;

    private Integer enable;

}
