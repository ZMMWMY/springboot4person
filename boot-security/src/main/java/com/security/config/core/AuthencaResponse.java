package com.security.config.core;

import lombok.Builder;

/**
 * Author : MrZ
 *
 * @Description Created in 13:48 on 2017/5/10.
 * Modified By :
 */
public class AuthencaResponse extends CustomResponse {

    private String token;

    AuthencaResponse(Integer code, String msg, Object body) {
        super(code, msg, body);
    }
}
