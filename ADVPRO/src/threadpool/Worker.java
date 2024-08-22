package threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Vignesh.V on 19/08/24.
 */ // Worker Class implementing Runnable (Factory Method Pattern)
class Worker extends Thread {

    private final BlockingQueue<Runnable> taskQueue;
    private final AtomicBoolean isShutDownInitiated;

    public Worker(BlockingQueue<Runnable> taskQueue, AtomicBoolean isShutDownInitiated) {
        this.taskQueue = taskQueue;
        this.isShutDownInitiated = isShutDownInitiated;
    }

    @Override
    public void run() {
        while (!isShutDownInitiated.get() || !taskQueue.isEmpty()) {
            try {
                Runnable task = taskQueue.take();
                task.run();
            } catch (InterruptedException e) {
                if (isShutDownInitiated.get()) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }
}
