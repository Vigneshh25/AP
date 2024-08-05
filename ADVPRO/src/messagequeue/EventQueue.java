package messagequeue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EventQueue {
    private final Queue<Message> queue;
    private final int maxSize;
    private final Lock lock;
    private final Condition notEmpty;
    private final Condition notFull;

    public EventQueue(int maxSize) {
        this.queue = new LinkedList<>();
        this.maxSize = maxSize;
        this.lock = new ReentrantLock();
        this.notEmpty = lock.newCondition();
        this.notFull = lock.newCondition();
    }

    public void enqueue(Message message) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == maxSize) {
                notFull.await();
            }
            queue.add(message);
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public Message dequeue() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                notEmpty.await();
            }
            Message message = queue.poll();
            notFull.signalAll();
            return message;
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }
}
