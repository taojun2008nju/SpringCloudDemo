package com.springcloud.dao.entity;

import java.io.Serializable;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author Administrator
 * @date 2020/10/21 16:26:00
 * @description es测试实体类
 */
@Data
@Document(indexName = "vas_info-2020.10.11", type = "doc")
public class EsTestEntity implements Serializable {

    @Id
    private String id;

    private String message;
}
