package Design_Datastructure.java8.concurrency.collections;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.*;

public class ConcurrentCollectionsDemo {

    public static void main(String[] args) throws InterruptedException {

        // ConcurrentHashMap example
        Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        Runnable mapWriterTask = () -> {
            for (int i = 0; i < 1000; i++) {
                concurrentMap.put(Thread.currentThread().getName() + " Key-" + i, i);
            }
        };

        Runnable mapReaderTask = () -> {
            for (int i = 0; i < 1000; i++) {
                String key = Thread.currentThread().getName() + " Key-" + i;
                Integer value = concurrentMap.get(key);
                if (value != null) {
                    System.out.println(Thread.currentThread().getName() + " Read: " + key + " -> " + value);
                }
            }
        };

        Thread mapWriter1 = new Thread(mapWriterTask);
        Thread mapWriter2 = new Thread(mapWriterTask);
        Thread mapReader = new Thread(mapReaderTask);

        mapWriter1.start();
        mapWriter2.start();
        mapWriter1.join();
        mapWriter2.join();
        mapReader.start();
        mapReader.join();

        System.out.println("ConcurrentHashMap size: " + concurrentMap.size());

        // CopyOnWriteArrayList example
        List<String> copyOnWriteList = new CopyOnWriteArrayList<>();
        copyOnWriteList.add("Initial");

        Runnable listWriterTask = () -> {
            for (int i = 0; i < 100; i++) {
                copyOnWriteList.add(Thread.currentThread().getName() + " Item-" + i);
            }
        };

        Runnable listReaderTask = () -> {
            for (String item : copyOnWriteList) {
                System.out.println(Thread.currentThread().getName() + " Read: " + item);
            }
        };

        Thread listWriter1 = new Thread(listWriterTask);
        Thread listWriter2 = new Thread(listWriterTask);
        Thread listReader = new Thread(listReaderTask);

        listWriter1.start();
        listWriter2.start();
        listWriter1.join();
        listWriter2.join();
        listReader.start();
        listReader.join();

        System.out.println("CopyOnWriteArrayList size: " + copyOnWriteList.size());

        // ConcurrentLinkedQueue example
        Queue<String> concurrentQueue = new ConcurrentLinkedQueue<>();
        Runnable queueProducerTask = () -> {
            for (int i = 0; i < 100; i++) {
                concurrentQueue.offer(Thread.currentThread().getName() + " QueueItem-" + i);
            }
        };

        Runnable queueConsumerTask = () -> {
            String item;
            while ((item = concurrentQueue.poll()) != null) {
                System.out.println(Thread.currentThread().getName() + " Polled: " + item);
            }
        };

        Thread queueProducer1 = new Thread(queueProducerTask);
        Thread queueProducer2 = new Thread(queueProducerTask);
        Thread queueConsumer = new Thread(queueConsumerTask);

        queueProducer1.start();
        queueProducer2.start();
        queueProducer1.join();
        queueProducer2.join();
        queueConsumer.start();
        queueConsumer.join();

        System.out.println("ConcurrentLinkedQueue size: " + concurrentQueue.size());

        // BlockingQueue example with Producer-Consumer pattern
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(50);

        Runnable producerTask = () -> {
            try {
                for (int i = 0; i < 50; i++) {
                    String item = Thread.currentThread().getName() + " ProducedItem-" + i;
                    blockingQueue.put(item);
                    System.out.println(Thread.currentThread().getName() + " Put: " + item);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Runnable consumerTask = () -> {
            try {
                for (int i = 0; i < 50; i++) {
                    String item = blockingQueue.take();
                    System.out.println(Thread.currentThread().getName() + " Took: " + item);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Thread producer1 = new Thread(producerTask);
        Thread producer2 = new Thread(producerTask);
        Thread consumer1 = new Thread(consumerTask);
        Thread consumer2 = new Thread(consumerTask);

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();

        producer1.join();
        producer2.join();
        consumer1.join();
        consumer2.join();

        System.out.println("BlockingQueue size: " + blockingQueue.size());
    }
}
