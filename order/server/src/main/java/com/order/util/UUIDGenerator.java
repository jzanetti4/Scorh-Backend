package com.order.util;

import java.util.UUID;

public class UUIDGenerator {
    public static synchronized String getUUID32() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}
