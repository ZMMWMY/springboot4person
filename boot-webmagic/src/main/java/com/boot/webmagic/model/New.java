package com.boot.webmagic.model;

import java.util.Date;

/**
 * Created by Z先生 on 2017/2/10.
 */
public class New {

    private Integer id;
    private String title;
    private String content;
    private String url;
    private Date pubDate;
    private String source;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public New( String title, String url, Date pubDate, String source) {
        this.title = title;
        this.url = url;
        this.pubDate = pubDate;
        this.source = source;
    }

    public New() {
    }
}
