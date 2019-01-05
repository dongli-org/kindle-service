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

import cn.wanli.kindle.domain.enums.TaskStatus;
import com.alibaba.fastjson.JSON;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 推送表
 *
 * @author wanli
 * @date 2019-01-05 21:09
 */
@Entity
@Table(name = "tb_task_push")
public class TaskPush implements Serializable {
    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_create_time")
    private LocalDateTime createTime;

    @Column(name = "task_status")
    private TaskStatus status;

    @Column(name = "task_change_time")
    private LocalDateTime changeTime;

    @ManyToOne
    @JoinColumn(name = "task_user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "task_push_id", referencedColumnName = "push_id")
    private PushEmail pushEmail;

    @ManyToOne
    @JoinColumn(name = "task_book_id", referencedColumnName = "book_id")
    private KindleBook book;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDateTime getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(LocalDateTime changeTime) {
        this.changeTime = changeTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PushEmail getPushEmail() {
        return pushEmail;
    }

    public void setPushEmail(PushEmail pushEmail) {
        this.pushEmail = pushEmail;
    }

    public KindleBook getBook() {
        return book;
    }

    public void setBook(KindleBook book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
