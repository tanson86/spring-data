package com.understanding.spring.data.spring_data.understanding.threads;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.random.RandomGenerator;

public class MyThreadLocal {
    public static void main(String[] args) throws InterruptedException {
        UserContextRegular ucr1 = new UserContextRegular("AR");
        UserContextRegular ucr2 = new UserContextRegular("BR");
        Thread t1 = new Thread(ucr1);
        Thread t2 = new Thread(ucr2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(UserContextRegular.concMap.size());

        UserContextThreadLocal uctl1 = new UserContextThreadLocal("ATL");
        UserContextThreadLocal uctl2 = new UserContextThreadLocal("BTL");
        new Thread(uctl1).start();
        new Thread(uctl2).start();

        ExecutorService eF =  Executors.newFixedThreadPool(5);
        eF.submit(()->"hi");
    }
}


class UserContextRegular implements Runnable{

     static Map<String,Integer> concMap = new ConcurrentHashMap<>();
    private String key;

    UserContextRegular(String key){
        this.key = key;
    }

    @Override
    public void run() {
        int val = new Random().nextInt(10);
        concMap.put(key,val);
    }
}

class UserContextThreadLocal implements Runnable{

    private static ThreadLocal<String> tl = new ThreadLocal<>();
    private String key;

    UserContextThreadLocal(String key){
        this.key = key;
    }

    @Override
    public void run() {
        tl.set(this.key);
        System.out.println(tl.get());
        tl.remove();
    }
}