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

import cn.wanli.kindle.domain.MidUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author wanli
 * @date 2019-01-06 21:17
 */
public interface MidUserRoleRepository extends JpaRepository<MidUserRole, Long> {
    /**
     * 通过UID和RID查找用户和角色的关系
     *
     * @param uid user id
     * @param rid role id
     * @return {@link MidUserRole} 用户个角色关系对象
     */
    Optional<MidUserRole> findByUserIdAndRoleIdAndDeleted(Long uid, Long rid, Boolean deleted);
}
