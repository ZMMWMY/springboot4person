package com.boot.webmagic.pipeline;

import com.boot.elasticsearch.es.EsClient;
import com.boot.webmagic.model.New;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Date;

/**
 * Created by Z先生 on 2017/2/13.
 */
public class IndexPipeline implements Pipeline {

    private String indexName;

    private String typeName;

    public IndexPipeline(String indexName,String typeName) {
        this.indexName = indexName;
        this.typeName = typeName;
    }

    public IndexPipeline() {
        this.indexName = "spider";
        this.typeName = "downloade";
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        New n = new New(resultItems.get("title"), resultItems.get("url"), new Date(), resultItems.get("source"));
        try {
            EsClient.createIndex(indexName, typeName, new ObjectMapper().writeValueAsString(n));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
