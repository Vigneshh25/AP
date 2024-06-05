package Design_Datastructure;

import java.util.Stack;

class QueueUsingSingleStack {
    private Stack<Integer> stack = new Stack<>();

    // Add element x to the end of the queue.
    public void enqueue(int x) {
        stack.push(x);
    }

    // Removes the element from the front of the queue and returns that element.
    public int dequeue() {
        if (stack.isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        if (stack.size() == 1) {
            return stack.pop();
        }
        int top = stack.pop();
        int result = dequeue();
        stack.push(top);
        return result;
    }

    // Get the front element.
    public int front() {
        if (stack.isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        if (stack.size() == 1) {
            return stack.peek();
        }
        int top = stack.pop();
        int result = front();
        stack.push(top);
        return result;
    }

    // Returns whether the queue is empty.
    public boolean empty() {
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        QueueUsingSingleStack queue = new QueueUsingSingleStack();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println(queue.front()); // Should print 1
        System.out.println(queue.dequeue()); // Should print 1
        System.out.println(queue.empty()); // Should print false
    }
}
