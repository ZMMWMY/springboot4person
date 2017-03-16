package com.boot.webmagic.processor;

import com.boot.webmagic.pipeline.ImgPipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by Z先生 on 2017/3/16.
 */
public class CookiesProcessor implements PageProcessor {

    private Site site = Site.me()
            .setRetryTimes(3).setSleepTime(1000)
            .setDomain("http://sp.ogurishun.jp/")
            .addCookie("__utma","133789260.1287150100.1489628116.1489628116.1489628116.1")
            .addCookie("__utmc","133789260")
            .addCookie("__utmz","133789260.1489628116.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none)")
            .addCookie("id","OSQGeQQibXwc6")
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31")
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
            .addHeader("Accept-Encoding", "gzip, deflate, sdch").addHeader("Accept-Language", "zh-CN,zh;q=0.8")
            .addHeader("Connection", "keep-alive");


    @Override
    public void process(Page page) {
        System.out.println(page.getHtml().toString());
        String html = page.getHtml().$("div#photo").toString();
        page.putField("html",html);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new CookiesProcessor()).addUrl("http://sp.ogurishun.jp/blog/oguri/article.php?id=1484787281").addPipeline(new ConsolePipeline()).thread(5).run();
    }
}
