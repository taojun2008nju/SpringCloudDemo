package com.springcloud.serverapi.service;

import com.springcloud.dao.entity.EsOrderEntity;
import java.util.List;

/**
 * @author Administrator
 * @date 2020/10/27 21:20:00
 * @description TODO
 */
public interface IEsTestService {

    boolean testEsRestClient(String keyword);

    List<EsOrderEntity> queryEsOrderEntity(EsOrderEntity esOrderEntity);
}
