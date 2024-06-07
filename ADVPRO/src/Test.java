import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by Vignesh.V on 05/06/24.
 */
class Queue
{
    private final Stack<Integer> stack = new Stack<>();
    public void enqueue(int x)
    {
        stack.push(x);
    }
    public int dequeue()
    {
        if(stack.isEmpty())
        {
            System.out.println("Stack is Empty");
            return -1;
        }
        if(stack.size() == 1)
            return stack.pop();
        int top = stack.pop();
        int result = dequeue();
        stack.push(top);
        return result;
    }
    public int peek()
    {
        if(stack.isEmpty())
        {
            System.out.println("Stack is Empty");
            return -1;
        }
        if(stack.size() == 1)
            return stack.peek();
        int top = stack.pop();
        int result = peek();
        stack.push(top);
        return result;
    }
}

public class Test {
    public static void main(String[] args) {
        Queue queue = new Queue();

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(20);

        System.out.println("Front element: " + queue.peek()); // Output: 10

        System.out.println("Dequeued element: " + queue.dequeue()); // Output: 10
        System.out.println("Dequeued element: " + queue.dequeue()); // Output: 20

//        System.out.println("Is queue empty? " + queue.isEmpty()); // Output: false

        queue.enqueue(40);
        queue.enqueue(50);
        queue.enqueue(60); // This will print "Queue is full. Cannot enqueue."
        queue.enqueue(40);

    }
}
