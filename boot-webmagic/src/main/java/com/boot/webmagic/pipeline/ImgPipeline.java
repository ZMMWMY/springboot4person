package com.boot.webmagic.pipeline;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
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
import java.util.List;
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
        CloseableHttpClient httpclient = HttpClients.createDefault();
        List<String> list = resultItems.get("imgStr");
        String fileName = resultItems.get("imgName");
        for (String imgStr : list) {
            File dir = new File(this.path);
            if (!dir.isDirectory()) {
                dir.mkdir();
            }

            File imgDir = new File(this.path+File.separator + fileName);
            if(!imgDir.exists()){
                imgDir.mkdir();
            }
            try {
                FileOutputStream fout = new FileOutputStream(getFile(imgDir+"\\" + UUID.randomUUID().toString() + ".jpg"));
                try {
                    HttpGet get = new HttpGet(imgStr);
                    get.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
                    HttpResponse response = httpclient.execute(get);
                    HttpEntity entity = response.getEntity();
                    DataInputStream in = new DataInputStream(entity.getContent());

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
    }

    public static void main(String[] args) {
        String s = "e:\\webmagic"+File.separator+"妹子";
        File file = new File(s);
        if (!file.exists()){
            file.mkdir();
        }
    }
}
