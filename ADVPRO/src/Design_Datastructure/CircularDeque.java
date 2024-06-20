package Design_Datastructure;

class CircularDeque {
    private final int[] data;
    private int front, rear;
    private int size;
    private final int capacity;

    public CircularDeque(int k) {
        data = new int[k];
        front = 0;
        rear = -1;
        size = 0;
        capacity = k;
    }

    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }

        front = (front - 1 + capacity) % capacity;
        data[front] = value;
        size++;
        return true;
    }

    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }

        rear = (rear + 1) % capacity;
        data[rear] = value;
        size++;
        return true;
    }

    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }

        front = (front + 1) % capacity;
        size--;
        return true;
    }

    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }

        rear = (rear - 1 + capacity) % capacity;
        size--;
        return true;
    }

    public int getFront() {
        if (isEmpty()) {
            return -1;
        }
        return data[front];
    }

    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
        return data[rear];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public static void main(String[] args) {
        CircularDeque deque = new CircularDeque(3);

        System.out.println(deque.insertLast(1)); // true
        System.out.println(deque.insertLast(2)); // true
        System.out.println(deque.insertFront(3)); // true
        System.out.println(deque.insertFront(4)); // false (deque is full)

        System.out.println(deque.getRear()); // 2
        System.out.println(deque.isFull()); // true

        System.out.println(deque.deleteLast()); // true
        System.out.println(deque.insertFront(4)); // true
        System.out.println(deque.getFront()); // 4
    }
}
