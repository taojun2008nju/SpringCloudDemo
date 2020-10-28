package com.springcloud.serverapi.service.impl;

import com.alibaba.fastjson.JSON;
import com.springcloud.dao.entity.EsOrderEntity;
import com.springcloud.serverapi.service.IEsTestService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @date 2020/10/27 21:20:00
 * @description TODO
 */
@Slf4j
@Service
public class EsTestServiceImpl implements IEsTestService {

    @Autowired
    RestHighLevelClient highLevelClient;

    @Override
    public boolean testEsRestClient(String keyword){
        SearchRequest searchRequest = new SearchRequest("apcpp");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.termQuery("product_id", keyword));
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchRequest.source(sourceBuilder);
        try {
            SearchResponse response = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            Arrays.stream(response.getHits().getHits())
                .forEach(i -> {
                    System.out.println(i.getIndex());
                    System.out.println(i.getSourceAsString());
                    System.out.println(i.getType());
                });
            System.out.println(response.getHits().getTotalHits().value);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * es查询测试
     */
    @Override
    public List<EsOrderEntity> queryEsOrderEntity(EsOrderEntity esOrderEntity) {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.from(0);
        sourceBuilder.size(10);
        // 具体查询结果的列名
//        sourceBuilder.fetchSource(new String[]{"product_id"}, new String[]{});

        // 查询条件设置
        BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
        if (StringUtils.isNotEmpty(esOrderEntity.getCode())) {
            MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("code", esOrderEntity.getCode());
            boolBuilder.must(matchQueryBuilder);
        }
        if (StringUtils.isNotEmpty(esOrderEntity.getCust_id())) {
            MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("cust_id", esOrderEntity.getCust_id());
            boolBuilder.must(matchQueryBuilder);
        }
        if (StringUtils.isNotEmpty(esOrderEntity.getProduct_id())) {
            MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("product_id", esOrderEntity.getProduct_id());
            boolBuilder.must(matchQueryBuilder);
        }
        if (esOrderEntity.getStart_date() != null) {
            RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("start_date");
            rangeQueryBuilder.gte(esOrderEntity.getStart_date());
            boolBuilder.must(rangeQueryBuilder);
        }
        if (esOrderEntity.getEnd_date() != null) {
            RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("end_date");
            rangeQueryBuilder.lte(esOrderEntity.getEnd_date());
            boolBuilder.must(rangeQueryBuilder);
        }
        sourceBuilder.query(boolBuilder);
        SearchRequest searchRequest = new SearchRequest("order");
//        searchRequest.types(type);
        searchRequest.source(sourceBuilder);
        List<EsOrderEntity> esOrderEntityList = new ArrayList<>();
        try {
            SearchResponse response = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            System.out.println(response);
            for (SearchHit searchHit : response.getHits()) {
                EsOrderEntity order = JSON.parseObject(searchHit.getSourceAsString(), EsOrderEntity.class);
                esOrderEntityList.add(order);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return esOrderEntityList;
    }
}
