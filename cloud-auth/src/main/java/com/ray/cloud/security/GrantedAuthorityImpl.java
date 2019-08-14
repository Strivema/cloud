package com.ray.cloud.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * 封装权限
 *
 * @author Ray.Ma
 * @date 2019/8/14 17:22
 */
public class GrantedAuthorityImpl implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}