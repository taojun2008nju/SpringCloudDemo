package com.springcloud.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;

/**
 * @author Administrator
 * @date 2020/8/30 12:38:00
 * @description TODO
 */
@Data
public class TestEntity implements Serializable {

    /**
     * 主键编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;
}
