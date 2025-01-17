package com.understanding.spring.data.spring_data.understanding.threads;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.*;

public class MyLocks {
    ReentrantLock rl = new ReentrantLock();
    ReadWriteLock rrwl = new ReentrantReadWriteLock();
    StampedLock sl = new StampedLock();
    Semaphore sem = new Semaphore(2); //allows 2 thread to acquire lock at same time.
    Condition c = rl.newCondition();

    public static void main(String[] args) {
        MyLocks ml1 = new MyLocks();
        MyLocks ml2 = new MyLocks();
        Thread t1 = new Thread(()-> ml1.method());
        Thread t2 = new Thread(()-> ml2.method());
        t1.start();
        t2.start();
    }

    public synchronized void method(){
        System.out.println("Thread "+Thread.currentThread().getName()+" started");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            System.out.println("Thread " + Thread.currentThread().getName() + " finished");
        }
    }

    public void lockWithReentrant(){
        try {
            rl.lock();
            System.out.println("Reentrant lock " + Thread.currentThread().getName() + " acquired");
            Thread.sleep(5000);
        }catch(Exception e){

        }finally{
            rl.unlock();
            System.out.println("Reentrant lock " + Thread.currentThread().getName() + " released");
        }
    }

    public void lockWithReadLock(){
        try {
            rrwl.readLock().lock();
            System.out.println("ReadWrite read lock " + Thread.currentThread().getName() + " acquired");
            Thread.sleep(5000);
        }catch(Exception e){

        }finally{
            rrwl.readLock().unlock();
            System.out.println("ReadWrite read lock " + Thread.currentThread().getName() + " released");
        }
    }

    public void lockWithWriteLock(){
        try {
            rrwl.writeLock().lock();
            System.out.println("ReadWrite read lock " + Thread.currentThread().getName() + " acquired");
            Thread.sleep(5000);
        }catch(Exception e){

        }finally{
            rrwl.writeLock().unlock();
            System.out.println("ReadWrite read lock " + Thread.currentThread().getName() + " released");
        }
    }

    public void lockWithStampedLock(){
        long stamp = sl.readLock();
        try {

            System.out.println("ReadWrite read lock " + Thread.currentThread().getName() + " acquired");
            Thread.sleep(5000);
        }catch(Exception e){

        }finally{
            sl.unlockRead(stamp);
            System.out.println("ReadWrite read lock " + Thread.currentThread().getName() + " released");
        }
    }
}
