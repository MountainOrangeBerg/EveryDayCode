package com.study.code.thread;

//使用wait与notify实现
public class ProducerConsumer {

    public static void main(String[] arg) {

        Resource resource = new Resource();
        // 生产者线程
        ProducerThread p1 = new ProducerThread(resource);
        ProducerThread p2 = new ProducerThread(resource);
        ProducerThread p3 = new ProducerThread(resource);
        // 消费者线程。测试时可以少开几个消费线程看看具体
        ConsumerThread c1 = new ConsumerThread(resource);
        ConsumerThread c2 = new ConsumerThread(resource);
        ConsumerThread c3 = new ConsumerThread(resource);

        p1.start();
        p2.start();
        p3.start();
        c1.start();
        c2.start();
        c3.start();

    }

}

// 编写资源类
class Resource {
//当前资源池数量
    private int currentSize = 0;
    //允许数量
    private int allowSize = 10;

    // 取走资源，如果当前资源大于0则可以移除（消费），移除之后唤醒生产线程。否则进入等待释放线程资源
    public synchronized void remove() {
        if (currentSize > 0) {
            currentSize--;
            System.out.println(Thread.currentThread().getName() + "消费一件资源，当前资源池有" + currentSize + "个");

            notifyAll();
        } else {

            // 没有资源 消费者进入等待状态
            try {
                System.out.println(Thread.currentThread().getName() + "当前资源过少，等待增加");
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

    public synchronized void add() {
        // 如果当前数量小于限制数量则可以增加，增加后唤醒消费者消费,否则等待消费，释放锁
        if (currentSize < allowSize) {
            currentSize++;
            System.out.println(Thread.currentThread().getName() + "生产一件资源，当前资源池有" + currentSize + "个");
            notifyAll();

        } else {

            try {
                System.out.println(Thread.currentThread().getName() + "当前资源过多，等待消费");
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

}
//消费线程
class ConsumerThread extends Thread {
    private Resource resource;

    ConsumerThread(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true) {
            //避免生产消费太快测试的时候看不到打印，休眠一秒
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //移除代表消费
            resource.remove();
        }

    }

}
//生产者线程
class ProducerThread extends Thread {
    private Resource resource;

    ProducerThread(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //生产
            resource.add();
        }

    }

}
