package com.bfz.completablefuture;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

class IntroductionTest {

    private final Introduction introduction;

    public IntroductionTest() {
        this.introduction = new Introduction();
    }

    @Test
    void starting() {
        CompletableFuture
                .supplyAsync(() -> introduction.sayHello())
                .thenAccept(result -> System.out.println(result));
        Assertions.assertEquals(true, true);
    }

    @Test
    void test_say_hello1() {
        String response = CompletableFuture
                .supplyAsync(() -> introduction.sayHello())
                .join();

        Assertions.assertEquals("Hello", response);
    }

    @Test
    void test_say_hello2() {
        String response = CompletableFuture
                .supplyAsync(() -> introduction.sayHello())
                .thenApply(element -> element.toUpperCase())
                .join();

        Assertions.assertEquals("HELLO", response);
    }

    // Until now, nothing interesting, right?

    @Test
    void test_say_hello_world1() {
        String hello = introduction.sayHello();
        String world = introduction.sayWorld();

        String response = hello + " " + world;

        Assertions.assertEquals("Hello World", response);
    }

    @Test
    void test_say_hello_world2() {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(introduction::sayHello);
        CompletableFuture<String> world = CompletableFuture.supplyAsync(introduction::sayWorld);

        String response = hello
                .thenCombine(world, (previous, current) -> previous + " " + current)
                .thenApply(String::toUpperCase)
                .join();

        Assertions.assertEquals("HELLO WORLD", response);
    }

}
