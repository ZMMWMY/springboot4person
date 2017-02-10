package com.boot.webmagic.processor;

import com.boot.webmagic.pipeline.ImgPipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Z先生 on 2017/2/9.
 */
public class ImgProcessor implements PageProcessor {

    private Site site = Site.me()
            .setRetryTimes(3).setSleepTime(1000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");


    @Override
    public void process(Page page) {
        String imgRegex = "http://mm.howkuai.com/wp-content/uploads/20[0-9]{2}[a-z]/[0-9]{1,4}/[0-9]{1,4}/[0-9]{1,4}.jpg";
        List<String> requests = page.getHtml().links().regex("http://www.meizitu.com/[a-z]/[0-9]{1,4}.html").all();
        page.addTargetRequests(requests);
        List<String> listProcess = page.getHtml().$("div#picture").regex(imgRegex).all();
//        List<String> l = page.getHtml().links().regex("http://mm.howkuai.com/wp-content/uploads/20[0-9]{2}[a-z]/[0-9]{1,4}/[0-9]{1,4}/[0-9]{1,4}.jpg").all();
//        System.out.println("***"+l.size());
        page.putField("imgStr",listProcess);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new ImgProcessor()).addUrl("http://www.meizitu.com/").addPipeline(new ImgPipeline("e:\\webmagic")).thread(5).run();

//        String regex = "http://mm.howkuai.com/wp-content/uploads/20[0-9]{2}[a-z]/[0-9]{1,4}/[0-9]{1,4}/[0-9]{1,4}.jp";
//        String str= "http://mm.howkuai.com/wp-content/uploads/2016a/09/03/01.jpg";
//        Pattern p = Pattern.compile(regex);
//        Matcher m = p.matcher(str);
//        System.out.println(m.find());
    }
}
