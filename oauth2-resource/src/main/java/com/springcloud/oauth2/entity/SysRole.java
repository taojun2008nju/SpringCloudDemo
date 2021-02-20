package com.springcloud.oauth2.entity;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author Administrator
 * @date 2021/2/18 21:46:00
 * @description TODO
 */
@Data
public class SysRole implements GrantedAuthority {

    private Integer id;

    private String roleName;

    private String roleDesc;

    //标记此属性不做json处理
    @JsonIgnore
    @Override
    public String getAuthority() {
        return roleName;
    }
}
