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

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author wanli
 * @date 2018-12-06 21:11
 */
@Entity
@Table(name = "tb_push")
public class KindlePush implements Serializable {
    @Id
    @Column(name = "push_id")
    private String id;

    @Column(name = "push_time")
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "push_kindle_id", referencedColumnName = "kd_id")
    private Kindle kindle;

    @ManyToOne
    @JoinColumn(name = "push_book_id", referencedColumnName = "book_id")
    private KindleBook kindleBook;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Kindle getKindle() {
        return kindle;
    }

    public void setKindle(Kindle kindle) {
        this.kindle = kindle;
    }

    public KindleBook getKindleBook() {
        return kindleBook;
    }

    public void setKindleBook(KindleBook kindleBook) {
        this.kindleBook = kindleBook;
    }

    @Override
    public String toString() {
        return "KindlePush{" +
                "id='" + id + '\'' +
                ", dateTime=" + dateTime +
                ", kindle=" + kindle +
                ", kindleBook=" + kindleBook +
                '}';
    }
}
