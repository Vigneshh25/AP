package Design_Datastructure;

import java.util.LinkedList;
import java.util.Queue;

class StackUsingSingleQueue {
    private Queue<Integer> queue = new LinkedList<>();

    // Push element x onto stack.
    public void push(int x) {
        queue.add(x);
        int size = queue.size();
        while (size > 1) {
            queue.add(queue.remove());
            size--;
        }
    }

    // Removes the element on top of the stack and returns that element.
    public int pop() {
        if (queue.isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return queue.remove();
    }

    // Get the top element.
    public int top() {
        if (queue.isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return queue.peek();
    }

    // Returns whether the stack is empty.
    public boolean empty() {
        return queue.isEmpty();
    }

    public static void main(String[] args) {
        StackUsingSingleQueue stack = new StackUsingSingleQueue();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.top()); // Should print 3
        System.out.println(stack.pop()); // Should print 3
        System.out.println(stack.empty()); // Should print false
    }
}
