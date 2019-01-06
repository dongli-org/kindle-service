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

/**
 * Kindle 设备信息
 *
 * @author wanli
 * @date 2018-12-06 21:15
 */
@Entity
@Table(name = "tb_kindle")
public class Kindle implements Serializable {
    @Id
    @Column(name = "kd_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "kd_name", length = 32)
    private String kindleName;

    @Column(name = "kd_email", length = 128)
    private String email;

    @ManyToOne
    @JoinColumn(name = "kd_user_id", referencedColumnName = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKindleName() {
        return kindleName;
    }

    public void setKindleName(String kindleName) {
        this.kindleName = kindleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
