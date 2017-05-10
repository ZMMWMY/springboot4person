package com.security.config.core;

import lombok.Builder;
import lombok.Data;

/**
 * Author : MrZ
 *
 * @Description Created in 13:49 on 2017/5/10.
 * Modified By :
 */
@Data
@Builder
public class CustomResponse<T> {

    public static final Integer CODE_SUCCESS = 200;

    public static final String MSG_SUCCESS = "操作成功";

    public static final Integer TOKEN_FAIL = 401;
    public static final String TOKEN_FAIL_MSG = "token无效";

    private Integer code;

    private String msg;

    private T body;

}
