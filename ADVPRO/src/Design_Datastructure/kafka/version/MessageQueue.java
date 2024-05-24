package Design_Datastructure.kafka.version;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageQueue {
    private final BlockingQueue<Message> queue = new LinkedBlockingQueue<>();

    public void produce(Message message) throws InterruptedException {
        queue.put(message);
    }

    public Message consume() throws InterruptedException {
        return queue.take();
    }
}
