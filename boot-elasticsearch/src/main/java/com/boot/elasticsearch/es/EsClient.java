package com.boot.elasticsearch.es;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Z先生 on 2017/2/13.
 */
public class EsClient {

    public static Client getTransport() {
        Settings settings = Settings.settingsBuilder()
                .put("cluster.name", "elasticsearch").build();



        TransportClient client = null;
        try {
            client = TransportClient.builder().settings(settings).build()
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.174.128"), 9300));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return client;
    }

    public static void createIndex(String indexName, String typeName, String json) {
        IndexResponse response = getTransport().prepareIndex(indexName, typeName).setSource(json).execute().actionGet();
        System.out.println("索引Id:" + response.getId());
    }

    public static void test(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("test","test");
        IndexResponse response = getTransport().prepareIndex("test","test").setSource(map).execute().actionGet();
        System.out.println(response.getId());

    }
}
