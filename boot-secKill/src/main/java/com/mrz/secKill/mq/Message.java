package com.mrz.secKill.mq;

import lombok.AllArgsConstructor;

import java.io.Serializable;

/**
 * Author : MrZ
 *
 * @Description Created in 14:03 on 2017/5/23.
 * Modified By :
 */
public class Message implements Serializable{

    private String key;

    private Object content;

    private Integer failNum = 0;

    public Message(String key, Object content) {
        this.key = key;
        this.content = content;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public Integer getFailNum() {
        return failNum;
    }

    public void setFailNum(Integer failNum) {
        this.failNum = failNum;
    }
}
