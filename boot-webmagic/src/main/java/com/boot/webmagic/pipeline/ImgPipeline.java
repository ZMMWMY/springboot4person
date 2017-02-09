package com.boot.webmagic.pipeline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.utils.FilePersistentBase;

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
        String filePath = this.path;

    }
}
