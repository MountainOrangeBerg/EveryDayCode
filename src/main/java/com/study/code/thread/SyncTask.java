package com.study.code.thread;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
     * <p>
     * 多线程分段处理List集合
     * </P>
     */

public class SyncTask{

    public void multiThreadedListHandler3() {
 
        // 开始时间
        long start = System.currentTimeMillis();
        List<String> list = new ArrayList<>(10000);
        for (long i = 1; i <= 20000000; i++) {
            list.add(i + "");
        }
        int listSize = list.size();
        //执行数量
        int limitNum = 1000;
        //线程数(也就是执行次数)
        int threadNum = listSize % limitNum == 0 ? listSize / limitNum : listSize / limitNum + 1;
        int pcount = Runtime.getRuntime().availableProcessors();
        //最大线程数控制
        int maxthreadNum = 5;
 
        ExecutorService executor = new ThreadPoolExecutor(5, maxthreadNum, 1, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(1000), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
 
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        //最大并发线程数控制
        final Semaphore semaphore = new Semaphore(maxthreadNum);
        List handleList = null;
        for (int i = 0; i < threadNum; i++) {
            if ((i + 1) == threadNum) {
                int startIndex = i * limitNum;
                int endIndex = list.size();
                handleList = list.subList(startIndex, endIndex);
            } else {
                int startIndex = i * limitNum;
                int endIndex = (i + 1) * limitNum;
                handleList = list.subList(startIndex, endIndex);
            }
            Sync task = new Sync(handleList, countDownLatch, semaphore);
            executor.execute(task);
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
            System.out.println("线程任务执行结束");
            System.err.println("执行任务消耗了 ：" + (System.currentTimeMillis() - start) + "毫秒");
        }
    }
 
 
 
 
 

static class Sync implements Runnable {
    private List<String> list;
    private CountDownLatch countDownLatch;
    private Semaphore semaphore;
 
    public Sync(List<String> list, CountDownLatch countDownLatch, Semaphore semaphore) {
        this.list = list;
        this.countDownLatch = countDownLatch;
        this.semaphore = semaphore;
    }
 
    @Override
    public void run() {
        if (!CollectionUtils.isEmpty(list)) {
            try {
                semaphore.acquire();
                list.stream().forEach(t -> {
                    //业务处理
                });
                System.out.println(Thread.currentThread().getName() + "线程：" + list);
                //  log.debug(String.format("%s", Thread.currentThread().getName() + "线程：" + list));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
        //线程任务完成
        countDownLatch.countDown();
    }
}
}