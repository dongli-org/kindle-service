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

package cn.wanli.kindle.persistence;

import cn.wanli.kindle.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author wanli
 * @date 2018-12-06 20:34
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 通过邮箱查找用户
     *
     * @param email 邮箱地址
     * @return {@link User }
     */
    Optional<User> findByEmail(String email);

    /**
     * 通过名字查找用户
     *
     * @param name 用户姓名
     * @return {@link User}
     */
    Optional<User> findByName(String name);

    /**
     * 根据关键字分页查找用户
     *
     * @param keyword  关键字
     * @param pageable 分页信息
     * @return {@link Page } User
     */
    Page<User> findAllByNameContaining(String keyword, Pageable pageable);

}
