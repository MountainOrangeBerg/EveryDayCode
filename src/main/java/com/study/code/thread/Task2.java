package com.study.code.thread;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
/**
 * 将集合按指定数量分组，list中的元素被平均分配到n个集合中(平均分配给坐席)
 */
public class Task2 {
    public void collectionElementsAreGroupedByAverage() {

        // 开始时间
        long start = System.currentTimeMillis();
        //数据
        List<String> list = new ArrayList<>(10);
        for (long i = 1; i <= 123; i++) {
            list.add(i + "");
        }
        //客服名单
        List<String> currentSeatsList = new ArrayList<>(10);
        for (long i = 1; i <= 12; i++) {
            currentSeatsList.add("customer" + i);
        }

        int listSize = list.size();
        //根据坐席名单计算分批数量,分批数量=listSize/坐席数量
        int limitNum = listSize % currentSeatsList.size() == 0 ? listSize / currentSeatsList.size() : listSize / currentSeatsList.size() + 1;
        //执行次数
        int batchNum = listSize % limitNum == 0 ? listSize / limitNum : listSize / limitNum + 1;
        //cpu
        int pcount = Runtime.getRuntime().availableProcessors();
        //最大线程数控制
        int maxthreadNum = 5;

        ExecutorService executor = new ThreadPoolExecutor(pcount, maxthreadNum, 1, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(batchNum), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());

        //CountDownLatch计数器闭锁
        CountDownLatch countDownLatch = new CountDownLatch(batchNum);
        //控制最大并发线程数量
        final Semaphore semaphore = new Semaphore(maxthreadNum);

        List handleList = null;
        for (int i = 0; i < batchNum; i++) {
            if ((i + 1) == batchNum) {
                int startIndex = i * limitNum;
                int endIndex = list.size();
                handleList = list.subList(startIndex, endIndex);
            } else {
                int startIndex = i * limitNum;
                int endIndex = (i + 1) * limitNum;
                handleList = list.subList(startIndex, endIndex);
            }
            Task1 task = new Task1(handleList, countDownLatch, semaphore, currentSeatsList.get(i));
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



    class Task1 implements Runnable {
        private List<String> list;
        private CountDownLatch countDownLatch;
        private Semaphore semaphore;
        private String currentSeats;


        public Task1(List<String> list, CountDownLatch countDownLatch, Semaphore semaphore, String currentSeats) {
            this.list = list;
            this.countDownLatch = countDownLatch;
            this.semaphore = semaphore;
            this.currentSeats = currentSeats;
        }

        @Override
        public void run() {
            if (!CollectionUtils.isEmpty(list)) {
                try {
                    semaphore.acquire();
                    list.stream().forEach(t -> {
                        //业务处理
                    });
                    String strs = String.format("客服:%s  线程:%s", currentSeats, Thread.currentThread().getName() + list);
                    System.out.println(strs);
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
 