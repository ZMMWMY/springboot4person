package com.exception;

import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by Z先生 on 2016/11/9.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = FileUploadBase.FileSizeLimitExceededException.class)
    public String re(){
        return "文件太大";
    }

    @ExceptionHandler(value = RuntimeException.class)
    public String runTime(){
        return "异常发生";
    }

}
