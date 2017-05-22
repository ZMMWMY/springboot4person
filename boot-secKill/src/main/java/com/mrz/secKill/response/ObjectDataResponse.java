package com.mrz.secKill.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;

/**
 * Author : MrZ
 *
 * @Description Created in 10:44 on 2017/5/22.
 * Modified By :
 */
@Data
@Builder
public class ObjectDataResponse<T> implements Serializable {

    public static final int DEFAULT_OK = 20000;

    private int code = DEFAULT_OK;

    private String msg;

    private T body;

}
