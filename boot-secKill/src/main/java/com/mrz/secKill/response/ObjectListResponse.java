package com.mrz.secKill.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Author : MrZ
 *
 * @Description Created in 10:59 on 2017/5/22.
 * Modified By :
 */
@Data
@Builder
public class ObjectListResponse<T> implements Serializable {

    public static final int DEFAULT_OK = 20000;

    private int code = DEFAULT_OK;

    private String msg;

    private List<T> dataSet;

}
