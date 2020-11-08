package com.springcloud.dao.entity.mongo;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Administrator
 * @date 2020/10/31 16:56:00
 * @description TODO
 */
@Data
@Document(collection = "test")
public class MongoDbTestEntity implements Serializable {

    @Id
    @Field(value = "id")
    private Long id;

    @Field(value = "title")
    private String title;

    @Field(value = "content")
    private MongoContent mongoContent;

    @Field(value = "create_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;

    @Data
    public class MongoContent implements Serializable {

        @Field(value = "content")
        private String content;

        @Field(value = "description")
        private String description;

        @Field(value = "author")
        private String author;
    }
}
