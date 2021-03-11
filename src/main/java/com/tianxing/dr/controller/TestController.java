package com.tianxing.dr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author tianxing
 */
@RestController
public class TestController {

    @GetMapping("/hello")
    public String test(){
        return "hello";
    }
}
