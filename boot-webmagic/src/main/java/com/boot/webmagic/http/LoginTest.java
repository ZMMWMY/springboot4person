package com.boot.webmagic.http;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Z先生 on 2017/3/17.
 */
public class LoginTest {

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpUtil.getHttpUtilInstance().getHttpClient();

        HttpGet homePage = new HttpGet("https://cha.isao.net/profile_oem/OEMLogin.php?product_name=m-up&param1=site&param2=T00069E20001MP001180");

        CloseableHttpResponse response = httpClient.execute(homePage);

        HttpEntity loginEntity = response.getEntity();

        String loginHtml = EntityUtils.toString(loginEntity);

        String ses_rec_id= loginHtml.split("<input type=\"hidden\" name=\"ses_rec_id\" value=\"")[1].split("\">")[0];

        List<NameValuePair> valuePairs = new LinkedList<NameValuePair>();

        valuePairs.add(new BasicNameValuePair("page_marker","1"));
        valuePairs.add(new BasicNameValuePair("original_id","ogurixx"));
        valuePairs.add(new BasicNameValuePair("original_password","ogurishunXx11"));
        valuePairs.add(new BasicNameValuePair("ctg_disc_flg","1"));
        valuePairs.add(new BasicNameValuePair("template","license.html"));
        valuePairs.add(new BasicNameValuePair("ses_rec_id",ses_rec_id));
        valuePairs.add(new BasicNameValuePair("product_name","m-up"));

        HttpPost loginPost = new HttpPost("https://cha.isao.net/profile_oem/OEMUsrPrfMenu.php");

        loginPost.setEntity(new UrlEncodedFormEntity(valuePairs,"utf-8"));

        CloseableHttpResponse response1 = httpClient.execute(loginPost);

        String redirct = EntityUtils.toString(response1.getEntity(),"utf-8");


        String ses_rec_id1= redirct.split("<input type=\"hidden\" name=\"ses_rec_id\" value=\"")[1].split("\">")[0];
        valuePairs = new LinkedList<NameValuePair>();
        valuePairs.add(new BasicNameValuePair("ses_rec_id",ses_rec_id1));

        HttpPost loginPost1 = new HttpPost("https://cha.isao.net/profile_oem/OEMRequest.php");

        loginPost1.setEntity(new UrlEncodedFormEntity(valuePairs,"utf-8"));

        CloseableHttpResponse response2 = httpClient.execute(loginPost1);

        System.out.println(EntityUtils.toString(response2.getEntity(),"utf-8"));


        HttpGet pageGet = new HttpGet("http://sp.ogurishun.jp/blog/oguri/article.php?id=1489298361");
        CloseableHttpResponse imageHtml = httpClient.execute(pageGet);

        String html2 = EntityUtils.toString(imageHtml.getEntity(),"utf-8");
        System.out.println(html2);


    }
}
