package com.fastdfs;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Created by Z先生 on 2016/12/22.
 */
@SpringBootApplication
@Import(FdfsClientConfig.class)
public class FastdfsStart {
    public static void main(String[] args) {
        SpringApplication.run(FastdfsStart.class, args);
    }
}
