package com.order.util;

import com.order.OrderApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class UUIDGeneratorTest extends OrderApplicationTests {

    @Test
    public void getUUID32() {
        log.info(UUIDGenerator.getUUID32());
    }
}