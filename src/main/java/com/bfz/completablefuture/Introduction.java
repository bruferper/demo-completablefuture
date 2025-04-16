package com.bfz.completablefuture;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Introduction {

    public String sayHello() {
        try {
            Thread.sleep(1000);
            log.info("sayHello invocation");
            return "Hello";
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String sayWorld() {
        try {
            Thread.sleep(1000);
            log.info("sayWorld invocation");
            return "World";
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
