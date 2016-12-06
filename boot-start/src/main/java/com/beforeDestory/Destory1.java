package com.beforeDestory;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * Created by Z先生 on 2016/11/18.
 */
@Component
public class Destory1 implements ExitCodeGenerator {
    public int getExitCode() {
        System.out.println("destty");
        return 0;
    }
}
