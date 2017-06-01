package com.security.config;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * Author : MrZ
 *
 * @Description Created in 15:49 on 2017/3/28.
 * Modified By :
 */
@RestControllerAdvice
public class MyExceptionHandler {
    private static Logger logger = Logger.getLogger(MyExceptionHandler.class);


    @ExceptionHandler(value = Exception.class)
    public Object defaultException(Exception e){
        logger.error(e.getMessage(),e);
        return "";
    }

    public static void main(String[] args) throws IOException {
        BASE64Encoder encoder = new BASE64Encoder();
        FileInputStream inputStream = new FileInputStream(new File("e://12.jpg"));
        byte [] bytes = new byte[1000000];
        inputStream.read(bytes);
        String  a = encoder.encode(bytes);
        FileOutputStream outputStream = new FileOutputStream(new File("e://a.txt"));
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
    }
    
}
