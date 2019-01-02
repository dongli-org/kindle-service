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

package cn.wanli.kindle.config.security;

import cn.wanli.kindle.domain.Group;
import cn.wanli.kindle.domain.User;
import cn.wanli.kindle.exception.ResourceNotFoundException;
import cn.wanli.kindle.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static cn.wanli.kindle.utils.CommonsUtils.isEmail;
import static java.util.stream.Collectors.toList;

/**
 * @author wanli
 * @date 2018-12-29 00:18
 */
@Service("userDetail")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDetails loadUserByUsername(String username) {
        Optional<User> userOpt;

        if (isEmail(username)) {
            userOpt = userRepository.findByEmail(username);
        } else {
            userOpt = userRepository.findByName(username);
        }

        return userOpt.map(user -> {
            List<SimpleGrantedAuthority> authes = user.getGroups().stream()
                    .map(Group::getRoles)
                    .flatMap(Collection::stream)
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                    .collect(toList());
            return new JwtUser(user.getId(), user.getName(), user.getPassword(), user.getEmail(), authes, user.getEnabled());
        }).orElseThrow(() -> new ResourceNotFoundException(String.format("查找的用户不存在: %s", username)));
    }
}
