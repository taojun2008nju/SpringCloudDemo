package com.springcloud.dao.entity.mongo;

import java.io.Serializable;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Administrator
 * @date 2020/10/31 16:56:00
 * @description TODO
 */
@Data
@Document(collection = "test")
public class MongoDbTestEntity implements Serializable {

    @Id
    private Long id;

    private String title;

    private String description;

    private String by;

    private String url;

}
