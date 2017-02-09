package com.boot.webmagic.pipeline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.utils.FilePersistentBase;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Z先生 on 2017/2/9.
 * 自定义持久化
 */
public class ImgPipeline extends FilePersistentBase implements Pipeline {


    public ImgPipeline() {
        setPath("data/webmagic");
    }

    public ImgPipeline(String path) {
        setPath(path);
    }

    public void process(ResultItems resultItems, Task task) {
        String imgStr = resultItems.get("img");
       // String imgName = resultItems.get("imgName");
        File dir = new File(this.path);
        if(!dir.isDirectory()){
            dir.mkdir();
        }
        try {
            FileOutputStream fout = new FileOutputStream(getFile(this.path+UUID.randomUUID().toString()+".jpg"));
            try {
                URL url = new URL(imgStr);
                DataInputStream in = new DataInputStream(url.openStream());
                byte[] tmp = new byte[1024];
                int l = -1;
                while ((l = in.read(tmp)) != -1) {
                    fout.write(tmp, 0, l);
                }
                fout.flush();
                fout.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        File file =new File("d:\\test");
        System.out.println(file.isDirectory());
    }
}
