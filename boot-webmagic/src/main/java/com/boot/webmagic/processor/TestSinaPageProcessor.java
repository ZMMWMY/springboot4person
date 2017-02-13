package com.boot.webmagic.processor;

import com.boot.elasticsearch.es.EsClient;
import com.boot.webmagic.model.New;
import com.boot.webmagic.pipeline.IndexPipeline;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Date;
import java.util.List;

/**
 * Created by Z先生 on 2017/2/13.
 */
public class TestSinaPageProcessor implements PageProcessor {
    private Site site = Site.me()
            .setRetryTimes(3).setSleepTime(1000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");


    @Override
    public void process(Page page) {
        String title = page.getHtml().xpath("//*[@id=\"artibodyTitle\"]/text()").toString();
        List<String> contents = page.getHtml().xpath("//div[@id=\"artibody\"]/p").all();
        String time = page.getHtml().xpath("//*[@id=\"navtimeSource\"]/text()").get();
        String source = page.getHtml().xpath("//*[@id=\"navtimeSource\"]/span/span/a/text()").get();
        String url = page.getUrl().toString();
        page.putField("title",title);
        page.putField("contents",contents);
        page.putField("time",time);
        page.putField("source",source);
        page.putField("url",url);

       /* New n = new New(title,time,new Date(),source);
        ObjectMapper mapper = new ObjectMapper();
        try {
            EsClient.createIndex("testnew","testnew",mapper.writeValueAsString(n));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new TestSinaPageProcessor()).addUrl("http://news.sina.com.cn/c/nd/2017-02-13/doc-ifyameqr7454243.shtml").addPipeline(new IndexPipeline()).run();
//        EsClient.test();
    }
}
