package com.boot.webmagic.http;

import org.apache.http.*;
import org.apache.http.client.CookieStore;
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
import org.apache.http.impl.client.*;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import java.io.*;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.springframework.util.ResourceUtils.getFile;

/**
 * Created by Z先生 on 2017/3/16.
 */
public class HttpUtil {

    static CookieStore cookieStore = new BasicCookieStore();

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
            clientContext.setCookieStore(cookieStore);
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
                .setCookieSpec(CookieSpecs.BROWSER_COMPATIBILITY).build();
        // 声明重定向策略对象
        LaxRedirectStrategy redirectStrategy = new LaxRedirectStrategy();

        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(httpClientConnectionManager)
                .setDefaultRequestConfig(requestConfig)
                .setRedirectStrategy(redirectStrategy)
                .setRetryHandler(myRetryHandler)
                .setDefaultCookieStore(cookieStore)
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


    public void post(String url, Map<String, String> param) {
        HttpPost httpPost = new HttpPost(url);
        try {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            if (param != null) {
                for (Map.Entry<String, String> entry : param.entrySet()) {
                    nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            }
            CloseableHttpResponse response = getHttpClient().execute(httpPost);
            HttpEntity entity = response.getEntity();
            System.out.println(EntityUtils.toString(entity));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void get(String url) {
        HttpGet get = new HttpGet(url);
        try {
            CloseableHttpResponse response = getHttpClient().execute(get);
            HttpEntity entity = response.getEntity();
            String html = EntityUtils.toString(entity, "UTF-8");
            String ses_rec_id = html.split("<input type=\"hidden\" name=\"ses_rec_id\" value=\"")[1].split("\">")[0];
            System.out.println(ses_rec_id);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getHtml(String url) {
        HttpGet get = new HttpGet(url);
        try {
            CloseableHttpResponse response = getHttpClient().execute(get);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public void downImg(String url) {

        File imgDir = new File("E:\\img");
        if (!imgDir.exists()) {
            imgDir.mkdir();
        }
        try {
            FileOutputStream fout = new FileOutputStream(getFile(imgDir + "\\" + UUID.randomUUID().toString() + ".jpg"));
            try {
                HttpGet get = new HttpGet(url);
                get.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
                HttpResponse response = getHttpClient().execute(get);
                HttpEntity entity = response.getEntity();
                DataInputStream in = new DataInputStream(entity.getContent());

                byte[] tmp = new byte[1024];
                int l = -1;
                while ((l = in.read(tmp)) != -1) {
                    fout.write(tmp, 0, l);
                }
                fout.flush();
                fout.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        //  getHttpUtilInstance().grabPageHTML();
        getHttpUtilInstance().get("https://cha.isao.net/profile_oem/OEMLogin.php?product_name=m-up&param1=site&param2=T00069E20001MP001103");
    }


}
