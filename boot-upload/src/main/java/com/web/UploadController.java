package com.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * Created by Z先生 on 2016/11/9.
 */
@RestController
public class UploadController {

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String upload(MultipartFile file){
        try {
            //路径
            String path=""+file.getOriginalFilename();
            BufferedOutputStream out=new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
            out.write(file.getBytes());
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            return "文件不存在";
        } catch (IOException e) {
            return "发生异常";
        }
        return "SUCCESS";
    }

    @RequestMapping(value = "/upload2",method = RequestMethod.POST)
    public String upload2(MultipartFile file){
        try {
            //路径
            File newFile=new File("E://"+file.getOriginalFilename());
            file.transferTo(newFile);
        } catch (FileNotFoundException e) {
            return "文件不存在";
        } catch (IOException e) {
            return "发生异常";
        } catch (Exception e){
            return "。。。";
        }
        return "SUCCESS";
    }


    @RequestMapping(value = "/upload3",method = RequestMethod.POST)
    public String upload2( ){
        if(1==1){
            throw new RuntimeException("");
        }

        return "SUCCESS";
    }
}
