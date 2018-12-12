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

package cn.wanli.kindle.service;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * @author wanli
 * @date 2018-12-06 22:51
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupServiceTest {

    @Test
    public void save() {
    }

    @Test
    public void findUser() {
    }

    @Test
    public void aas() {
        RestTemplate restTemplate = new RestTemplate();
        Object forObject = restTemplate.getForObject("http://127.0.0.1:8443", Object.class);
        System.out.println(JSON.toJSONString(forObject));
    }
}