package com.boot.webmagic.http;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by alive on 2017/3/21.
 */
public class UrlQueue {

    public static Set<String> urlPool = new HashSet<String>();

    private static BlockingQueue articleQueue = new ArrayBlockingQueue(100);

    private static BlockingQueue imgQueue = new ArrayBlockingQueue(100);

    private static BlockingQueue pageQueue = new ArrayBlockingQueue(20);


    public static void addArticleUrl(String url) throws InterruptedException {
        articleQueue.put(url);
    }

    public static String removeArticleUrl() throws InterruptedException {
        return (String) articleQueue.take();

    }

    public static void addImgUrl(String url) throws InterruptedException {
        imgQueue.put(url);
    }

    public static String removeImgUrl() throws InterruptedException {
        return (String) imgQueue.take();

    }

    public static void addPageUrl(String url) throws InterruptedException {
        pageQueue.put(url);
    }

    public static String removePageUrl() {
        return (String) pageQueue.poll();

    }
}
