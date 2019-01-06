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
 * @date 2018-12-06 21:30
 */
@Entity
@Table(name = "tb_role")
public class Role implements Serializable {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name", length = 32, unique = true, nullable = false)
    private String name;

    @Column(name = "role_desc", length = 64)
    private String desc;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "tb_user_role",
            joinColumns = {@JoinColumn(name = "ur_role_id")},
            inverseJoinColumns = {@JoinColumn(name = "ur_user_id")})
    private List<User> users;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "tb_role_per",
            joinColumns = {@JoinColumn(name = "rp_role_id")},
            inverseJoinColumns = {@JoinColumn(name = "rp_per_id")})
    private List<Permission> permissions;

    public Role() {
    }

    public Role(String name, String desc) {
        this.name = name;
        this.desc = desc;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
