package com.springcloud.serverapi.controller;

import com.springcloud.common.common.CommonResult;
import com.springcloud.dao.entity.es.EsLogEntity;
import com.springcloud.dao.entity.es.EsOrderEntity;
import com.springcloud.dao.entity.es.EsTestEntity;
import com.springcloud.dao.entity.TestEntity;
import com.springcloud.dao.repository.es.IEsLogEntityRepository;
import com.springcloud.dao.repository.es.IEsTestEntityRepository;
import com.springcloud.serverapi.service.IEsTestService;
import com.springcloud.serverapi.util.ElasticsearchTransportClientUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @date 2020/8/8 15:46:00
 * @description es控制类
 */
@RestController
@RequestMapping("/es")
@Slf4j
public class EsApiController {

    /**
     * SpringBoot-es集成
     */
    @Autowired
    private IEsTestEntityRepository esTestEntityRepository;
    /**
     * 2、查  ++:全文检索（根据整个实体的所有属性，可能结果为0个）
     * @param
     * @return
     */
    @GetMapping("/select/{q}")
    public List<EsTestEntity> testSearch(@PathVariable String q) {
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(q);
        Iterable<EsTestEntity> searchResult = esTestEntityRepository.search(builder);
        Iterator<EsTestEntity> iterator = searchResult.iterator();
        List<EsTestEntity> list = new ArrayList<EsTestEntity>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }
    @PostMapping("/saveEs")
    public CommonResult saveEs(@RequestBody EsTestEntity esTestEntity) {
        esTestEntity.setId(UUID.randomUUID().toString());
        esTestEntity.setTime(new Date());
        EsTestEntity entityResult = esTestEntityRepository.save(esTestEntity);
        CommonResult<TestEntity> commonResult = new CommonResult("0", "success", null);
        return commonResult;
    }
    @PostMapping("/deleteEs")
    public CommonResult deleteEs(@RequestParam(value = "id") String id) {
        esTestEntityRepository.deleteById(id);
        CommonResult<TestEntity> commonResult = new CommonResult("0", "success", null);
        return commonResult;
    }

    @Autowired
    private IEsLogEntityRepository esLogEntityRepository;
    @GetMapping("/log/select/{q}")
    public List<EsLogEntity> vasLogSearch(@PathVariable String q) {
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(q);
        Iterable<EsLogEntity> searchResult = esLogEntityRepository.search(builder);
        Iterator<EsLogEntity> iterator = searchResult.iterator();
        List<EsLogEntity> list = new ArrayList<EsLogEntity>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    /**
     * ElasticsearchRestTemplate
     */
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    @GetMapping("/testEsQuery/{q}")
    public CommonResult testEsQuery(@PathVariable String q) {
        Query query = new StringQuery(q);
        SearchHits<EsTestEntity> esTestEntitySearchHit = elasticsearchRestTemplate.search(query, EsTestEntity.class);
        CommonResult<TestEntity> commonResult = new CommonResult("0", "success", esTestEntitySearchHit);
        return commonResult;
    }

    @RequestMapping("/createIndex")
    public CommonResult createIndex(@RequestParam(value = "index") String index) {
        ElasticsearchTransportClientUtil.createIndex(index);
        CommonResult<TestEntity> commonResult = new CommonResult("0", "success", null);
        return commonResult;
    }
    @RequestMapping("/deleteIndex")
    public CommonResult deleteIndex(@RequestParam(value = "index") String index) {
        ElasticsearchTransportClientUtil.deleteIndex(index);
        CommonResult<TestEntity> commonResult = new CommonResult("0", "success", null);
        return commonResult;
    }

    @Autowired
    private IEsTestService esTestService;
    @RequestMapping("/testEsRestClient")
    public CommonResult testEsRestClient(@RequestParam(value = "index") String index) {
        esTestService.testEsRestClient(index);
        CommonResult<TestEntity> commonResult = new CommonResult("0", "success", null);
        return commonResult;
    }

    @RequestMapping("/queryTest")
    public CommonResult queryTest(@RequestBody EsOrderEntity esOrderEntity) {
        List<EsOrderEntity> esOrderEntityList = esTestService.queryEsOrderEntity(esOrderEntity);
        CommonResult<TestEntity> commonResult = new CommonResult("0", "success", esOrderEntityList);
        return commonResult;
    }

}
