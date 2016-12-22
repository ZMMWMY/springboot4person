package com.fastdfs.web;

import com.fastdfs.util.FastdfsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by Z先生 on 2016/12/22.
 */
@RestController
public class TestController {

    @Autowired
    FastdfsUtil fastdfsUtil;


    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String upload(MultipartFile file) throws IOException {
        return fastdfsUtil.uploadFile(file);
    }


}
