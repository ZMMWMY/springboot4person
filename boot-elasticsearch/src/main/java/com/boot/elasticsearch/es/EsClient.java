package com.boot.elasticsearch.es;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
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

    public static void searchByFields(String ... strings){
        Client client =getTransport();
        SearchResponse response = client.prepareSearch("sina").setQuery(QueryBuilders.queryStringQuery(strings[0])).execute().actionGet();
        SearchHits searchHits = response.getHits();
        System.out.println("共检索到"+searchHits.getTotalHits()+"条记录");
        SearchHit [] hits = searchHits.getHits() ;
        for (SearchHit hit: hits) {
            System.out.println(hit.getSourceAsString());
        }
    }

    public static void searchIndex(){
        Client client =getTransport();
        SearchResponse response = client.prepareSearch("sina").execute().actionGet();
        SearchHits searchHits = response.getHits();
        System.out.println("共检索到"+searchHits.getTotalHits()+"条记录");
        SearchHit [] hits = searchHits.getHits() ;
        for (SearchHit hit: hits) {
            System.out.println(hit.getSourceAsString());
        }
    }


    public static void test(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("test","test");
        IndexResponse response = getTransport().prepareIndex("test","test").setSource(map).execute().actionGet();
        System.out.println(response.getId());

    }

    public static void main(String[] args) {
        searchByFields("第一");
   //     searchIndex();
    }
}
