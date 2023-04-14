package com.study.code.thread;

/**
 * 从上面结果看出，使用synchroniz修饰方法会在每个线程中按顺序依次执行。
 */
public class Test implements Runnable {
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
        Test t1=new Test();
        for(int i=0;i<10;i++){
        	Thread t11=new Thread(t1);
            t11.start();
        }
	}
}
