package Design_Datastructure;

/**
 * Created by Vignesh.V on 08/08/23.
 */
public class Queue {
    private int maxSize;
    private int front;
    private int rear;
    private int[] array;

    public Queue(int size) {
        maxSize = size + 1; // One extra space for better handling full/empty conditions
        array = new int[maxSize];
        front = 0;
        rear = -1;
    }

    public boolean isEmpty() {
        return front == (rear + 1) % maxSize;
    }

    public boolean isFull() {
        return front == (rear + 2) % maxSize;
    }

    public void enqueue(int value) {
        if (isFull()) {
            System.out.println("Queue is full. Cannot enqueue.");
            return;
        }
        rear = (rear + 1) % maxSize;
        array[rear] = value;
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty. Cannot dequeue.");
            return -1; // Return a default value indicating failure
        }
        int value = array[front];
        front = (front + 1) % maxSize;
        return value;
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return -1; // Return a default value indicating failure
        }
        return array[front];
    }

    public static void main(String[] args) {
        Queue queue = new Queue(5);

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        System.out.println("Front element: " + queue.peek()); // Output: 10

        System.out.println("Dequeued element: " + queue.dequeue()); // Output: 10
        System.out.println("Dequeued element: " + queue.dequeue()); // Output: 20

        System.out.println("Is queue empty? " + queue.isEmpty()); // Output: false

        queue.enqueue(40);
        queue.enqueue(50);
        queue.enqueue(60); // This will print "Queue is full. Cannot enqueue."
    }
}

