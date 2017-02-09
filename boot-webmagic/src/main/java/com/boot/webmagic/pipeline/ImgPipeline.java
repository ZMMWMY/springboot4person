package com.boot.webmagic.pipeline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.utils.FilePersistentBase;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Map;

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

    @Override
    public void process(ResultItems resultItems, Task task) {
        String imgStr = resultItems.get("img");
        try {
            FileOutputStream fout =new FileOutputStream(getFile(this.path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
