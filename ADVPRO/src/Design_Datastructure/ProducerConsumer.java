import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ProducerConsumer {
    private final List<Integer> buffer;
    private final int bufferSize;
    private final Lock lock;
    private final Condition notFull;
    private final Condition notEmpty;

    public ProducerConsumer(int bufferSize) {
        this.buffer = new ArrayList<>(bufferSize);
        this.bufferSize = bufferSize;
        this.lock = new ReentrantLock();
        this.notFull = lock.newCondition();
        this.notEmpty = lock.newCondition();
    }

    public void produce(int item) throws InterruptedException {
        lock.lock();
        try {
            while (buffer.size() == bufferSize) {
                System.out.println("Buffer is full, producer is waiting");
                notFull.await();
            }
            buffer.add(item);
            System.out.println("Produced " + item + ", buffer state: " + buffer);
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void consume() throws InterruptedException {
        lock.lock();
        try {
            while (buffer.isEmpty()) {
                System.out.println("Buffer is empty, consumer is waiting");
                notEmpty.await();
            }
            int item = buffer.remove(0);
            System.out.println("Consumed " + item + ", buffer state: " + buffer);
            notFull.signalAll();
        } finally {
            lock.unlock();
        }
    }
}

class Producer implements Runnable {
    private final ProducerConsumer pc;
    private final int[] items;

    public Producer(ProducerConsumer pc, int[] items) {
        this.pc = pc;
        this.items = items;
    }

    @Override
    public void run() {
        try {
            for (int item : items) {
                Thread.sleep((int) (Math.random() * 1000));
                pc.produce(item);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Consumer implements Runnable {
    private final ProducerConsumer pc;
    private final int consumeCount;

    public Consumer(ProducerConsumer pc, int consumeCount) {
        this.pc = pc;
        this.consumeCount = consumeCount;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < consumeCount; i++) {
                Thread.sleep((int) (Math.random() * 1000));
                pc.consume();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        int bufferSize = 5;
        ProducerConsumer pc = new ProducerConsumer(bufferSize);

        int[] itemsToProduce = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int produceCount = itemsToProduce.length;

        Thread producerThread = new Thread(new Producer(pc, itemsToProduce));
        Thread consumerThread = new Thread(new Consumer(pc, produceCount));

        producerThread.start();
        consumerThread.start();
    }
}
