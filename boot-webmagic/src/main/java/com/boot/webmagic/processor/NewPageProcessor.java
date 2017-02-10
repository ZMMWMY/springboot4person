package com.boot.webmagic.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Z先生 on 2017/2/10.
 */
public class NewPageProcessor implements PageProcessor {

    private Site site = Site.me()
            .setRetryTimes(3).setSleepTime(1000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");


    @Override
    public void process(Page page) {
   //     List<String> list = page.getHtml().xpath("//div[@id=\"artibody\"]/p/text()").all();
    //    page.putField("content",list);
        System.out.println(page.getHtml().xpath("//div[@class=\"pages-date\"]/span").get());
      //  page.putField("source",page.getHtml().xpath("//div[@class=\"pages-date\"]/span").get());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new NewPageProcessor()).addUrl("http://news.sina.com.cn/2017-02-10/doc-ifyameqr7398349.shtml").thread(1).run();
    }
}
