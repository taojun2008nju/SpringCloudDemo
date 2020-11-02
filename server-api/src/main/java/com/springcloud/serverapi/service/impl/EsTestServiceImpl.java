package com.springcloud.serverapi.service.impl;

import com.alibaba.fastjson.JSON;
import com.springcloud.common.common.CommonResult;
import com.springcloud.dao.entity.es.EsOrderEntity;
import com.springcloud.serverapi.service.IEsTestService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
     * 创建索引
     * @param index
     * @throws IOException
     */
    @Override
    public void createIndex(String index) throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(index);
        CreateIndexResponse createIndexResponse = highLevelClient.indices().create(request,     RequestOptions.DEFAULT);
        System.out.println("createIndex: " + JSON.toJSONString(createIndexResponse));
    }

    /**
     * 判断索引是否存在
     * @param index
     * @return
     * @throws IOException
     */
    @Override
    public boolean existsIndex(String index) throws IOException {
        GetIndexRequest request = new GetIndexRequest(index);
        request.humanReadable(true);
        boolean exists = highLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println("existsIndex: " + exists);
        return exists;
    }

    /**
     * 增加记录
     * @param indexName
     * @param type
     * @param order
     * @throws IOException
     */
    @Override
    public void add(String indexName, String type, EsOrderEntity order) throws IOException {
        IndexRequest indexRequest = new IndexRequest(indexName);
        indexRequest.source(JSON.toJSONString(order), XContentType.JSON);
        IndexResponse indexResponse = highLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        if (indexResponse != null) {
            String id = indexResponse.getId();
            String index = indexResponse.getIndex();
            long version = indexResponse.getVersion();
            log.info("index:{},id:{}", index, id);
            if (indexResponse.getResult() == DocWriteResponse.Result.CREATED) {
                System.out.println("新增文档成功!" + index + "-" + id + "-" + version);
            } else if (indexResponse.getResult() == DocWriteResponse.Result.UPDATED) {
                System.out.println("修改文档成功!");
            }
            // 分片处理信息
            ReplicationResponse.ShardInfo shardInfo = indexResponse.getShardInfo();
            if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
                System.out.println("分片处理信息.....");
            }
            // 如果有分片副本失败，可以获得失败原因信息
            if (shardInfo.getFailed() > 0) {
                for (ReplicationResponse.ShardInfo.Failure failure : shardInfo.getFailures()) {
                    String reason = failure.reason();
                    System.out.println("副本失败原因：" + reason);
                }
            }
        }
        System.out.println("add: " + JSON.toJSONString(indexResponse));
    }


    /**
     * 测试更新数据
     * @param id
     * @param order
     * @return
     */
    @Override
    public CommonResult testESUpdate(String id, EsOrderEntity order) {
        UpdateRequest updateRequest = new UpdateRequest("test_es", id);
        Map<String, Object> map = new HashMap<>();
        map.put("cust_id", order.getCust_id());
        updateRequest.doc(map);
        try {
            UpdateResponse updateResponse = highLevelClient.update(updateRequest, RequestOptions.DEFAULT);
            if (updateResponse.getResult() == DocWriteResponse.Result.UPDATED) {
                return new CommonResult("200", "更新成功", null);
            } else {
                return new CommonResult("10002", "删除失败", null);
            }
        } catch (IOException e) {
            log.error("Method:testESUpdate exception:", e);
            return new CommonResult("1003", "删除异常", null);
        }
    }

    /**
     * 删除数据
     * @param id
     * @param indexName
     * @return
     */
    @Override
    public CommonResult testESDelete(String id, String indexName) {
        DeleteRequest deleteRequest = new DeleteRequest(indexName);
        deleteRequest.id(id);
        try {
            DeleteResponse deleteResponse = highLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
            if (deleteResponse.getResult() == DocWriteResponse.Result.NOT_FOUND) {
                return new CommonResult("1001", "删除失败", null);
            } else {
                return new CommonResult("200", "删除成功", null);
            }
        } catch (IOException e) {
            log.error("Mehotd:testESDelete exception:", e);
            return new CommonResult("1003", "删除异常", null);
        }
    }

    /**
     * 批量插入
     * @param list
     * @throws IOException
     */
    @Deprecated
    @Override
    public void bulkPutIndex(List<Map<String, Object>> list) throws IOException {
        if (CollectionUtils.isEmpty(list)){
            return;
        }
        String index = "test";
        String type = "test";
        int size = list.size();
        BulkRequest request = new BulkRequest();
        for (int i = 0; i < size; i++) {
            Map<String, Object> map = list.get(i);
            //这里必须每次都使用new IndexRequest(index,type),不然只会插入最后一条记录（这样插入不会覆盖已经存在的Id，也就是不能更新）
            // request.add(new IndexRequest(index,type).opType("create").id(map.remove("id").toString()).source(map));
            request.add(new IndexRequest(index, type, String.valueOf(map.remove("id"))).source(map, XContentType.JSON));
        }
        highLevelClient.bulk(request, RequestOptions.DEFAULT);
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


    /**
     * 单条件检索
     * @param fieldKey
     * @param fieldValue
     * @return
     */
    public MatchPhraseQueryBuilder uniqueMatchQuery(String fieldKey, String fieldValue){
        MatchPhraseQueryBuilder matchPhraseQueryBuilder = QueryBuilders.matchPhraseQuery(fieldKey,fieldValue);
        return matchPhraseQueryBuilder;
    }

    /**
     * 多条件检索并集，适用于搜索比如包含腾讯大王卡，滴滴大王卡的用户
     * @param fieldKey
     * @param queryList
     * @return
     */
    public BoolQueryBuilder orMatchUnionWithList(String fieldKey, List<String> queryList){
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        for (String fieldValue : queryList){
            boolQueryBuilder.should(QueryBuilders.matchPhraseQuery(fieldKey,fieldValue));
        }
        return boolQueryBuilder;
    }

    /**
     * 范围查询，左右都是闭集
     * @param fieldKey
     * @param start
     * @param end
     * @return
     */
    public RangeQueryBuilder rangeMathQuery(String fieldKey, String start, String end){
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(fieldKey);
        rangeQueryBuilder.gte(start);
        rangeQueryBuilder.lte(end);
        return rangeQueryBuilder;
    }

    /**
     * 根据中文分词进行查询
     * @param fieldKey
     * @param fieldValue
     * @return
     */
    public MatchQueryBuilder matchQueryBuilder(String fieldKey, String fieldValue){
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery(fieldKey,fieldValue).analyzer("ik_smart");
        return matchQueryBuilder;
    }
}
