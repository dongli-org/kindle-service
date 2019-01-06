/*
 * kindle electronic book push service
 *
 * Copyright (C) 2018 - wanli <wanlinus@qq.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package cn.wanli.kindle.domain;

import com.alibaba.fastjson.JSON;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author wanli
 * @date 2018-12-06 18:21
 */
@Entity
@Table(name = "tb_user")
public class User implements Serializable {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户名 唯一
     */
    @Column(name = "user_name", length = 32, nullable = false, unique = true)
    private String name;
    /**
     * 用户昵称
     */
    @Column(name = "user_nickname", length = 32)
    private String nickname;

    @Column(name = "user_password", length = 60, nullable = false)
    private String password;

    @Column(name = "user_email", length = 128, unique = true)
    private String email;

    /**
     * 是否可用
     */
    @Column(name = "user_enabled", nullable = false)
    private Boolean enabled = false;

    /**
     * 是否系统推送
     */
    @Column(name = "user_sys_push")
    private Boolean systemPush = false;

    /**
     * 账户是否过期
     */
    @Column(name = "account_non_expired", nullable = false)
    private Boolean accountNonExpired;

    /**
     * 用户的Kindle设备
     */
    @OneToMany(mappedBy = "user")
    private List<Kindle> kindles;

    /**
     * 用户所属组
     */
    @ManyToMany(mappedBy = "users")
    private List<Group> groups;

    public User() {
    }

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getSystemPush() {
        return systemPush;
    }

    public void setSystemPush(Boolean systemPush) {
        this.systemPush = systemPush;
    }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public List<Kindle> getKindles() {
        return kindles;
    }

    public void setKindles(List<Kindle> kindles) {
        this.kindles = kindles;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
