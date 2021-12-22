package com.kiple.api.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestMain {

    public static String DEFAULT_HOST="http://47.254.241.181";

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        int i=0;
        while(i++ < 1){
            executorService.execute(new PaymentTest());
        }
    }
}
