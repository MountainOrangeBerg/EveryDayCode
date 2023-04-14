package com.study.code.thread;

/**
 * 只是稍微修改了一处，结果就有很大不同，为什么会这样呢？
 * 我们发现上面两个代码，一个是在开始值创建一个Runnable的对象，一个是在for循环中每次都创建一个新的对象，
 * 就是因为synchronized是不能锁住不同对象的线程的，只能锁住同一个对象的线程，也就是说锁住的是方法所属的主体对象自身。
 */
public class Test1 implements Runnable {
    static int i=0;
    public synchronized void test(){
        System.out.println("当前线程为"+i);
        i++;
    }
    public void run(){
        test();
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        for(int i=0;i<10;i++){
            Test t1=new Test();
            Thread t11=new Thread(t1);
            t11.start();
        }
    }
}

