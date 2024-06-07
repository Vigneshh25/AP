import com.sun.xml.internal.messaging.saaj.packaging.mime.util.LineInputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

class CustomThreadPool
{
    private final BlockingQueue<Runnable> taskQueue;
    private final List<WorkerThread> threadList;

    public CustomThreadPool(int capacity) {
        this.taskQueue = new LinkedBlockingQueue<>();
        this.threadList = new ArrayList<>();
        for(int i=0;i<capacity;i++)
        {
            WorkerThread workerThread = new WorkerThread();
            workerThread.start();
            threadList.add(workerThread);
        }
    }

    private void submit(Runnable runnable) throws InterruptedException {
        taskQueue.put(runnable);
    }


    private class WorkerThread extends Thread
    {
        private volatile boolean running = true;

        @Override
        public synchronized void run() {
            while (running)
            {
                try {
                    Runnable runnable = taskQueue.take();
                    runnable.run();
                } catch (InterruptedException e) {
                    running = false;
                    Thread.currentThread().interrupt();
                }
            }
        }
        public void stopThread()
        {
            running = false;
            interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CustomThreadPool customThreadPool = new CustomThreadPool(3);
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for(int i=0;i<10;i++)
        {
            int finalI = i;
            customThreadPool.submit(() -> {
                System.out.println("Task ID is " + finalI + " Task is run by Thread " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
    }
}