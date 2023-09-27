import java.util.ArrayList;
import java.util.List;

public class MyPriorityQueue<T extends Comparable<T>> {
    private List<T> heap;

    public MyPriorityQueue() {
        heap = new ArrayList<>();
    }

    public void insert(T item) {
        heap.add(item);
        int currentIndex = heap.size() - 1;
        heapifyUp(currentIndex);
    }

    public T remove() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority queue is empty");
        }

        T root = heap.get(0);
        T lastItem = heap.remove(heap.size() - 1);

        if (!isEmpty()) {
            heap.set(0, lastItem);
            heapifyDown(0);
        }

        return root;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    private void heapifyUp(int index) {
        int parentIndex = (index - 1) / 2;

        while (index > 0 && heap.get(index).compareTo(heap.get(parentIndex)) > 0) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    private void heapifyDown(int index) {
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;
        int largestIndex = index;

        if (leftChildIndex < heap.size() && heap.get(leftChildIndex).compareTo(heap.get(largestIndex)) > 0) {
            largestIndex = leftChildIndex;
        }

        if (rightChildIndex < heap.size() && heap.get(rightChildIndex).compareTo(heap.get(largestIndex)) > 0) {
            largestIndex = rightChildIndex;
        }

        if (largestIndex != index) {
            swap(index, largestIndex);
            heapifyDown(largestIndex);
        }
    }

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public static void main(String[] args) {
        MyPriorityQueue<Integer> priorityQueue = new MyPriorityQueue<>();
        
        priorityQueue.insert(5);
        priorityQueue.insert(3);
        priorityQueue.insert(8);
        priorityQueue.insert(1);
        priorityQueue.insert(10);

        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.remove());
        }
    }
}
