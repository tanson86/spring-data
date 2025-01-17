package com.understanding.spring.data.spring_data.understanding.threads;

import java.util.concurrent.*;

public class MyCompletableFuture {

    ThreadPoolExecutor tpe = new ThreadPoolExecutor(1,1,60, TimeUnit.SECONDS,new ArrayBlockingQueue<>(10));

    public void compFutApis() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> supp = CompletableFuture.supplyAsync(() -> 1, tpe);
        //System.out.println(supp.get());
        CompletableFuture<Integer> moreOps = supp.thenApplyAsync(i->i+1);
        supp.thenCompose(i->CompletableFuture.supplyAsync(()->i+1));

        CompletableFuture<Integer> one = CompletableFuture.supplyAsync(() -> 1, tpe);
        CompletableFuture<Integer> two = CompletableFuture.supplyAsync(() -> 1, tpe);
        CompletableFuture<Integer> thenCombine = one.thenCombine(two,(o,t)->o+t);
        System.out.println(thenCombine.get());
    }

}
