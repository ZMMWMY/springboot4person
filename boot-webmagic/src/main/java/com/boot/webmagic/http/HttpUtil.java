package com.boot.webmagic.http;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Z先生 on 2017/3/16.
 */
public class HttpUtil {

    private PoolingHttpClientConnectionManager httpClientConnectionManager = null;

    private static final HttpUtil HTTP_UTIL = new HttpUtil();

    public static HttpUtil getHttpUtilInstance() {
        return HTTP_UTIL;
    }

    private HttpUtil() {
        initHttpClient();

    }

    private void initHttpClient() {
        //创建httpclient连接池
        httpClientConnectionManager = new PoolingHttpClientConnectionManager();
        //设置连接池最大数量
        httpClientConnectionManager.setMaxTotal(50);
        //设置单个路由最大连接数量
        httpClientConnectionManager.setDefaultMaxPerRoute(5);
    }

    HttpRequestRetryHandler myRetryHandler = new HttpRequestRetryHandler() {
        public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
            if (executionCount >= 3) {
                // 超过三次则不再重试请求
                return false;
            }
            if (exception instanceof InterruptedIOException) {
                // Timeout
                return false;
            }
            if (exception instanceof UnknownHostException) {
                // Unknown host
                return false;
            }
            if (exception instanceof ConnectTimeoutException) {
                // Connection refused
                return false;
            }
            if (exception instanceof SSLException) {
                // SSL handshake exception
                return false;
            }
            HttpClientContext clientContext = HttpClientContext.adapt(context);
            HttpRequest request = clientContext.getRequest();
            boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
            if (idempotent) {
                // Retry if the request is considered idempotent
                return true;
            }
            return false;
        }
    };

    public CloseableHttpClient getHttpClient() {
        // 创建全局的requestConfig
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(600000)
                .setSocketTimeout(30000)
                .setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
        // 声明重定向策略对象
        LaxRedirectStrategy redirectStrategy = new LaxRedirectStrategy();

        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(httpClientConnectionManager)
                .setDefaultRequestConfig(requestConfig)
                .setRedirectStrategy(redirectStrategy)
                .setRetryHandler(myRetryHandler)
                .build();
        return httpClient;
    }

    /**
     * 方法名：createSSLClientDefault
     * 描述：针对https采用SSL的方式创建httpclient
     *
     * @return
     */
    public static CloseableHttpClient createSSLClientDefault() {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                //信任所有
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);

            return HttpClients.custom().setSSLSocketFactory(sslsf).build();

        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }

        return HttpClients.createDefault();
    }


    public void post(String url, Map<String,String> param) {
        HttpPost httpPost = new HttpPost(url);
        try {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            if(param!=null){
                for (Map.Entry<String, String> entry : param.entrySet()) {
                    nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                httpPost.setEntity(new UrlEncodedFormEntity(nvps,"utf-8"));
            }
            CloseableHttpResponse response = getHttpClient().execute(httpPost);
            HttpEntity entity = response.getEntity();
            System.out.println(EntityUtils.toString(entity));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void get(String url){
        HttpGet get = new HttpGet(url);
        try {
            CloseableHttpResponse response = getHttpClient().execute(get);
            HttpEntity entity = response.getEntity();
            String html = EntityUtils.toString(entity,"UTF-8");
            String ses_rec_id= html.split("<input type=\"hidden\" name=\"ses_rec_id\" value=\"")[1].split("\">")[0];
            System.out.println(ses_rec_id);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
      //  getHttpUtilInstance().grabPageHTML();
        getHttpUtilInstance().get("https://cha.isao.net/profile_oem/OEMLogin.php?product_name=m-up&param1=site&param2=T00069E20001MP001103");
    }

    public void grabPageHTML() throws Exception {
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet("http://www.baidu.com/");
        HttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();
        String html = EntityUtils.toString(entity, "GBK");

        // releaseConnection等同于reset，作用是重置request状态位，为下次使用做好准备。
        // 其实就是用一个HttpGet获取多个页面的情况下有效果；否则可以忽略此方法。
        httpget.releaseConnection();

        System.out.println(html);
    }

}
