package com.springcloud.dao.entity.es;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Administrator
 * @date 2020/10/21 16:26:00
 * @description es测试实体类
 */
@Data
@Document(indexName = "es_test", type = "doc")
public class EsTestEntity implements Serializable {

    @Id
    private String id;

    private String message;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date time;
}
