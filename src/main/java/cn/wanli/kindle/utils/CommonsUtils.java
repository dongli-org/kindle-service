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

package cn.wanli.kindle.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author wanli
 * @date 2018-12-07 00:15
 */
public final class CommonsUtils {
    private CommonsUtils() {
        throw new AssertionError("工具类不用于实例化");
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonsUtils.class);

    /**
     * 验证是否为邮箱
     *
     * @param str 验证字符串
     * @return 是邮箱返回true 否则返回false
     */
    public static boolean isEmail(String str) {
        if (str == null) {
            return false;
        }
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        return str.matches(regEx1);
    }

    /**
     * 保存文件
     *
     * @param multipartFile 上传的文件
     * @param rootPath      root目录
     * @param filePath      文件子目录
     * @return 文件路径
     */
    public static String saveFile(MultipartFile multipartFile, String rootPath, String filePath) {
        String filename;
        filename = "kindle" + multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf('.'));
        File file = new File(rootPath + filePath + filename);
        try (FileOutputStream fos = new FileOutputStream(file);
             FileInputStream fs = (FileInputStream) multipartFile.getInputStream()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fs.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            LOGGER.error("保存文件出现错误", e);
        }
        return filePath + filename;
    }
}
