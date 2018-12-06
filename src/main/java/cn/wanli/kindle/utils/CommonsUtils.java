package cn.wanli.kindle.utils;


import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * @author wanli
 * @date 2018-12-07 00:15
 */
public final class CommonsUtils {
    private CommonsUtils() {
        throw new AssertionError("工具类不用于实例化");
    }

    public static String primaryKey() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * md5摘要
     *
     * @param password 需要加密的原文
     * @return 返回md5摘要
     */
    public static String md5Encrypt(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
    }
}
