package Design_Datastructure;

/**
 * Created by Vignesh.V on 08/08/23.
 */
public class DynamicArray<T> {
    private Object[] array;
    private int size;
    private int capacity;

    public DynamicArray(int initialCapacity) {
        array = new Object[initialCapacity];
        size = 0;
        capacity = initialCapacity;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (T) array[index];
    }

    public void set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        array[index] = element;
    }

    public void add(T element) {
        if (size == capacity) {
            resize();
        }
        array[size++] = element;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        capacity *= 2;
        Object[] newArray = new Object[capacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    public static void main(String[] args) {
        DynamicArray<Integer> dynamicArray = new DynamicArray<>(3);

        dynamicArray.add(10);
        dynamicArray.add(20);
        dynamicArray.add(30);

        System.out.println("Size: " + dynamicArray.size()); // Output: 3

        System.out.println("Element at index 1: " + dynamicArray.get(1)); // Output: 20

        dynamicArray.set(1, 25);
        System.out.println("Updated element at index 1: " + dynamicArray.get(1)); // Output: 25

        dynamicArray.remove(0);
        System.out.println("Size after removal: " + dynamicArray.size()); // Output: 2
    }
}

