package com.springcloud.dao.entity.es;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author Administrator
 * @date 2020/10/28 20:58:00
 * @description TODO
 */
@Data
@Document(indexName = "order", type = "order")
public class EsOrderEntity implements Serializable {

    private long id;

    private String code;

    private String state;

    private String cust_id;

    private String product_id;

    private String product_type;

    private String business_type;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date start_date;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date end_date;
}
