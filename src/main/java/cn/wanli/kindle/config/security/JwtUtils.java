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

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;

/**
 * @author wanli
 * @date 2018-12-31 02:13
 */
public final class JwtUtils {
    private JwtUtils() {
        throw new AssertionError();
    }

    public static String generateToken(UserDetails userDetails) {
        Long now = System.currentTimeMillis();
        long expired = now + 60 * 60 * 1000;

        return Jwts.builder()
                .setClaims(new HashMap<>(1))
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(expired))
                .signWith(SignatureAlgorithm.HS512, "wanli")
                .compact();
    }
}
