package com.springcloud.serverapi.service;

import com.springcloud.common.common.CommonResult;
import com.springcloud.dao.entity.es.EsOrderEntity;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @date 2020/10/27 21:20:00
 * @description TODO
 */
public interface IEsTestService {

    boolean testEsRestClient(String keyword);

    /**
     * 创建索引
     * @param index
     * @throws IOException
     */
    void createIndex(String index) throws IOException;

    /**
     * 判断索引是否存在
     * @param index
     * @return
     * @throws IOException
     */
    boolean existsIndex(String index) throws IOException;

    /**
     * 增加记录
     * @param index
     * @param type
     * @param order
     * @throws IOException
     */
    void add(String index, String type, EsOrderEntity order) throws IOException;

    /**
     * 测试更新数据
     * @param id
     * @param order
     * @return
     */
    CommonResult testESUpdate(String id, EsOrderEntity order);

    /**
     * 删除记录
     * @param id
     * @param indexName
     * @return
     */
    CommonResult testESDelete(String id, String indexName);

    /**
     * 批量插入
     * @param list
     * @throws IOException
     */
    void bulkPutIndex(List<Map<String, Object>> list) throws IOException;

    /**
     * es查询测试
     * @param esOrderEntity
     * @return
     */
    List<EsOrderEntity> queryEsOrderEntity(EsOrderEntity esOrderEntity);
}
