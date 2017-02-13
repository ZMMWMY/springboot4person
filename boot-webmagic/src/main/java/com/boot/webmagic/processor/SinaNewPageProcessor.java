package com.boot.webmagic.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Z先生 on 2017/2/10.
 */
public class SinaNewPageProcessor implements PageProcessor {

    private Site site = Site.me()
            .setRetryTimes(3).setSleepTime(1000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    private String newsInfoRegex = "http:\\/\\/news.sina.com.cn(\\/\\w+)*\\/\\d{4}-\\d{2}-\\d{2}\\/doc-\\w*\\d*.shtml";


    private String newsListRegex = "^http:\\/\\/news.sina.com.cn/(\\w+)*\\/$";


    public boolean mather(String str){
        Pattern pattern =Pattern.compile(newsInfoRegex,34);
        Matcher m = pattern.matcher(str);
        return m.matches();
    }

    @Override
    public void process(Page page) {

        if (page.getUrl().regex(newsListRegex).match()) {
          /*  List<String> list1 = page.getHtml().links().all();
            List<String> list2 = new ArrayList<>();
            for (String str:list1) {
                if(mather(str)){
                    list2.add(str);
                }
            }*/
          //指定group 原来，group是针对（）来说的，group（0）就是指的整个串，group（1） 指的是第一个括号里的东西，group（2）指的第二个括号里的东西
          List list = page.getHtml().links().regex(newsInfoRegex,0).all();
            page.addTargetRequests(list);
            page.addTargetRequests(page.getHtml().links().regex(newsListRegex).all());
        } else  if (page.getUrl().regex(newsInfoRegex).match()){
            String title = page.getHtml().xpath("//div[@id=\"artibodyTitle\"]/h1/text()").toString();
            List<String> contents = page.getHtml().xpath("//div[@id=\"artibody\"]/p").all();
            String time = page.getHtml().xpath("//div[@id=\"navtimeSource\"]/span").get();
            String source =  page.getHtml().xpath("//*[@id=\"navtimeSource\"]/span/span/a").get();
        }
    }



    @Override
    public Site getSite() {
        return site;
    }



    public static void main(String[] args) {
        Spider.create(new SinaNewPageProcessor()).addUrl("http://news.sina.com.cn/world/").thread(5).run();

    }
}
