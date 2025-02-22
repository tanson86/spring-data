package com.understanding.spring.data.spring_data.understanding.threads;

import aj.org.objectweb.asm.commons.TryCatchBlockSorter;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class Sample {
    String s = new String("A");
    Lock l = new ReentrantLock();
    Condition c = l.newCondition();
    //Condition lc = l.newCondition();
    static boolean number=true;
    public static void main(String[] args) {


    }

    private void test(List<? super Integer> objs){
        objs.add(4);
    }

    private void orderOfClosingResources() throws Exception {
        try (AutoCloseableResourcesFirst af = new AutoCloseableResourcesFirst();
             AutoCloseableResourcesSecond as = new AutoCloseableResourcesSecond()) {

            af.doSomething();
            as.doSomething();
        }

        try (Scanner scanner = new Scanner(new File("testRead.txt"));
             PrintWriter writer = new PrintWriter(new File("testWrite.txt"))) {
            while (scanner.hasNext()) {
                writer.print(scanner.nextLine());
            }
        }
    }

    private static int num;
    private static boolean ready;

    private static class Reader extends Thread {

        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
            }

            System.out.println(num);


        }
    }


    void printNumbers(List<Integer> numbers) throws InterruptedException {

        for(int i:numbers){
            //l.lock();
            //System.out.println("number "+number);
            synchronized (s) {
                while (!number) {
                    s.wait();
                }
                number = false;
                System.out.print(i);
                s.notifyAll();
            }
            //l.unlock();
        }
    }

    void printChars(List<Character> chars) throws InterruptedException {
        for(Character i:chars){
            //System.out.println("char "+number);
            synchronized (s) {
                //l.lock();
                while (number) {
                    s.wait();
                }
                number = true;
                System.out.print(i);
                s.notifyAll();
                //l.unlock();
            }
        }
    }

}


class AutoCloseableResourcesFirst implements AutoCloseable {

    public AutoCloseableResourcesFirst() {
        System.out.println("Constructor -&gt; AutoCloseableResources_First");
    }

    public void doSomething() {
        System.out.println("Something -&gt; AutoCloseableResources_First");
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closed AutoCloseableResources_First");
    }
}

class AutoCloseableResourcesSecond implements AutoCloseable {

    public AutoCloseableResourcesSecond() {
        System.out.println("Constructor -&gt; AutoCloseableResources_Second");
    }

    public void doSomething() {
        System.out.println("Something -&gt; AutoCloseableResources_Second");
    }

    @Override
    public void close() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        System.out.println("Closed AutoCloseableResources_Second");
    }

    public synchronized <T> void put(T item) {
        List<Object> queue = List.of();
        while (queue.size() == 1) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        if (queue.isEmpty()) {
            notifyAll();
        }
        queue.add(item);
    }

}
