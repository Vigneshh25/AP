package Design_Datastructure;

public class Stack {
    private int maxSize;
    private int top;
    private int[] array;

    public Stack(int size) {
        maxSize = size;
        array = new int[maxSize];
        top = -1; // Initialize top as -1 to indicate an empty stack
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public void push(int value) {
        if (isFull()) {
            System.out.println("Stack is full. Cannot push.");
            return;
        }
        array[++top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty. Cannot pop.");
            return -1; // Return a default value indicating failure
        }
        return array[top--];
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return -1; // Return a default value indicating failure
        }
        return array[top];
    }

    public static void main(String[] args) {
        Stack stack = new Stack(5);

        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("Top element: " + stack.peek()); // Output: 30

        System.out.println("Popped element: " + stack.pop()); // Output: 30
        System.out.println("Popped element: " + stack.pop()); // Output: 20

        System.out.println("Is stack empty? " + stack.isEmpty()); // Output: false

        stack.push(40);
        stack.push(50);
        stack.push(60); // This will print "Stack is full. Cannot push."
    }
}
