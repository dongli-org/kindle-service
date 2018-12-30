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

package cn.wanli.kindle;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * @author wanli
 * @date 2018-12-06 16:25
 */
public class DateTestDemo {
    public static void main(String[] args) {
//        LocalDate date = LocalDate.now();
//        System.out.println(date);
//        LocalDateTime time = LocalDateTime.now();
////        Stream.iterate(0, n -> n + 1).limit(100).forEach(n -> System.out.println(LocalDateTime.now()));
//        System.out.println(date.get(ChronoField.YEAR));
//
//        DateTimeFormatter
//        Long start = System.currentTimeMillis();
//        Stream.iterate(0, n -> ++n).limit(10000).forEach(System.out::println);
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);
//        long startTime = System.currentTimeMillis();
//        for (int i = 0; i < 10000; ++i) {
//            System.out.println(i);
//        }
//        long endTime = System.currentTimeMillis();
//        System.out.println(end - start);
//        System.out.println(endTime - startTime);

        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime.format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm")));

    }

    @Test
    public void asd() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("asd"));
    }
}
