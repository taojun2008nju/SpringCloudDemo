package com.springcloud.serverapi.repository;

import com.springcloud.dao.entity.EsTestEntity;
import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Administrator
 * @date 2020/10/21 16:37:00
 * @description TODO
 */
public interface IEsTestEntityRepository extends ElasticsearchRepository<EsTestEntity, String> {

    List<EsTestEntity> findByMessage(String message);
}
