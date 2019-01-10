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

package cn.wanli.kindle.entity;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * @author wanli
 * @date 2019-01-10 21:35
 */
public class BookEntity implements Serializable {
    private Long id;
    private String contributor;
    private String date;

    private String title;
    private String asin;
    private String authors;
    private String pic;

    public BookEntity() {
    }

    public BookEntity(Long id, String contributor, String date, String title, String asin, String authors, String pic) {
        this.id = id;
        this.contributor = contributor;
        this.date = date;
        this.title = title;
        this.asin = asin;
        this.authors = authors;
        this.pic = pic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContributor() {
        return contributor;
    }

    public void setContributor(String contributor) {
        this.contributor = contributor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
