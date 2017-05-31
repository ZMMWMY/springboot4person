package com.mrz.secKill.common;

import com.mrz.secKill.response.ObjectDataResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Author : MrZ
 *
 * @Description Created in 17:12 on 2017/5/31.
 * Modified By :
 */
@Slf4j
@RestControllerAdvice
public class ExceptionHandler {


    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public Object defaultException(Exception e) {
        log.error(e.getMessage(), e);
        return ObjectDataResponse.builder().msg(e.getMessage()).build();
    }
}
