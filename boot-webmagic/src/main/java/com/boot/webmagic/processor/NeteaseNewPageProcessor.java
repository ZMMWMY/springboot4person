package com.boot.webmagic.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by Z先生 on 2017/2/11.
 */
public class NeteaseNewPageProcessor implements PageProcessor {

    private Site site = Site.me()
            .setRetryTimes(3).setSleepTime(1000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    public static final String URL_LIST = "http://news\\.163\\.com/special/\\w+/\\w+\\.html";

    public static final String URL_POST = "http://news\\.163\\.com/.+\\.html";


    @Override
    public void process(Page page) {
        if(page.getUrl().regex("http://news\\.163\\.com/shehui").match()){
            page.addTargetRequests(page.getHtml().links().regex(URL_POST).all());
            page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
        }else{
            System.out.println(page.getHtml().xpath("//div[@id=\"epContentLeft\"]/h1").toString());
            page.putField("title", (page.getHtml().xpath("//div[@id=\"epContentLeft\"]/h1").toString()));
         /*   page.putField("content",(page.getHtml().xpath("//div[@id='endText']").toString()));
            page.putField("create", (page.getHtml().xpath("//div[@class=\"ep-time-soure cDGray\"]").toString()));
            page.putField("source", (page.getHtml().xpath("//a[@id=\"ne_article_source\"]/text()").toString()));
            page.putField("url", page.getUrl().get());*/
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new NeteaseNewPageProcessor())
                .addUrl("http://news.163.com/shehui")
                .thread(5)
                .run();
    }
}
