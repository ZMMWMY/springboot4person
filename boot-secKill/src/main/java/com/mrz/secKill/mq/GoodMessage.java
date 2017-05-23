package com.mrz.secKill.mq;

import lombok.AllArgsConstructor;

/**
 * Author : MrZ
 *
 * @Description Created in 15:35 on 2017/5/23.
 * Modified By :
 */
@AllArgsConstructor
public class GoodMessage {

    private String mobile;

    private String url;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
