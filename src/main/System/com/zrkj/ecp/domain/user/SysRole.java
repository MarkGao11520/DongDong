package com.zrkj.ecp.domain.user;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by gaowenfeng on 2017/2/5.
 */
public class SysRole {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
