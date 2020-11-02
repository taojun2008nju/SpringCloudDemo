package com.springcloud.dao.entity.es;

import java.io.Serializable;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author Administrator
 * @date 2020/10/24 17:11:00
 * @description es日志实体类
 */
@Data
@Document(indexName = "vas_info-2020.10.24", type = "doc")
public class EsLogEntity implements Serializable {

    @Id
    private String host;

    private String message;

    private String path;

    private String timestamp;
}
