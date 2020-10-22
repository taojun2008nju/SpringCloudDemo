package com.springcloud.serverapi.controller;

import com.springcloud.common.common.CommonResult;
import com.springcloud.dao.entity.EsTestEntity;
import com.springcloud.dao.entity.TestEntity;
import com.springcloud.serverapi.repository.IEsTestEntityRepository;
import com.springcloud.serverapi.service.IApiService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @date 2020/8/8 15:46:00
 * @description Api控制类
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class ApiController {

    @Autowired
    private IApiService apiService;

    @RequestMapping(value = {"/", "/index"})
    @ResponseBody
    public String index() {
        return "index";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hello " + name;
    }

    @RequestMapping("/testDb")
    @ResponseBody
    public CommonResult testDb(@RequestParam(name = "id", defaultValue = "1") long id) {
        log.info("Method:testDb id:{}", id);
        TestEntity testEntity = apiService.selectById(id);
        CommonResult<TestEntity> commonResult = new CommonResult("0", "success", testEntity);
        return commonResult;
    }

    @Autowired
    private IEsTestEntityRepository esTestEntityRepository;
    @RequestMapping("/testEs")
    @ResponseBody
    public CommonResult testEs(@RequestParam(name = "message") String message) {
        log.info("Method:testEs message:{}", message);
        List<EsTestEntity> esTestEntityList = esTestEntityRepository.findByMessage(message);
        CommonResult<TestEntity> commonResult = new CommonResult("0", "success", esTestEntityList);
        return commonResult;
    }

    /**
     * 2、查  ++:全文检索（根据整个实体的所有属性，可能结果为0个）
     * @param q
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
    @PostMapping("/save")
    public CommonResult add(@RequestBody EsTestEntity esTestEntity) {
        esTestEntityRepository.save(esTestEntity);
        CommonResult<TestEntity> commonResult = new CommonResult("0", "success", null);
        return commonResult;
    }
}
