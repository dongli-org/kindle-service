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
import java.time.LocalDateTime;

/**
 * @author wanli
 * @date 2019-01-06 21:06
 */
@Entity
@Table(name = "tb_mid_role_per")
public class MidRolePermission implements Serializable {
    @Id
    @Column(name = "rp_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rp_time")
    private LocalDateTime dateTime;

    @Column(name = "rp_deleted")
    private Boolean deleted;

    @ManyToOne
    @JoinColumn(name = "rp_role_id", referencedColumnName = "role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "rp_per_id", referencedColumnName = "per_id")
    private Permission permission;

    public MidRolePermission() {
    }

    public MidRolePermission(Role role, Permission permission) {
        this.role = role;
        this.permission = permission;
        dateTime = LocalDateTime.now();
        deleted = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
