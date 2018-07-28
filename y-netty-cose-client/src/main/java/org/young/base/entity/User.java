package org.young.base.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * User: Young
 * Date: 2018/7/24 0024
 * Time: 15:43
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Entity
public class User extends AbstractEntity implements UserDetails {

    /**
     * 普通用户
     */
    public static final String ROLE_USER = "ROLE_USER";

    /**
     * 管理员用户
     */
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    /**
     * 登录邮箱
     */
    @Column(unique = true)
    @Email
    private String email;

    /**
     * 登录手机
     */
    @Column(unique = true)
    @Pattern(regexp = "^1[0-9]{10}$")
    private String phone;

    /**
     * 登录用户名
     */
    @JSONField
    @Column(unique = true)
    private String name;

    /**
     * 登录密码，MD5加密
     */
    @JSONField(serialize = false)
    @Column(nullable = false)
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        if ("admin".equals(getName())) {
            authorities.add(new SimpleGrantedAuthority(ROLE_ADMIN));
        } else {
            authorities.add(new SimpleGrantedAuthority(ROLE_USER));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        if (!StringUtils.isEmpty(name)) {
            return name;
        } else if (!StringUtils.isEmpty(phone)) {
            return phone;
        } else {
            return email;
        }
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
