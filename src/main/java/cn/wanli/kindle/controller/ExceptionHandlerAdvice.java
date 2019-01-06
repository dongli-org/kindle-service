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

package cn.wanli.kindle.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * 全局异常处理
 *
 * @author wanli
 * @date 2018-12-12 17:23
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    /**
     * 处理所有服务器端抛出的异常
     *
     * @param e       异常对象
     * @param request 请求对象
     * @return 返回状态码404
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity exceptionHandler(Exception e, WebRequest request) {
        LOGGER.error("服务器错误 <{}>", request.getContextPath(), e);
        return ResponseEntity.notFound().build();
    }
}
