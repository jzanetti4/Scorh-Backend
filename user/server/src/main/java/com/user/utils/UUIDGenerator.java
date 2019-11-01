package com.user.utils;

import java.util.UUID;

/**
 * generate the distinct id in distributed environment
 * @description: UUIDGenerator
 * @author: Hangqi Yu
 * @date: Created in 2019-10-10 16:21
 * @version: V1.0
 * @modified: Hangqi Yu
 */
public class UUIDGenerator {
    public static synchronized String getUUID32() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}
