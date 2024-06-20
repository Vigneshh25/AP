package Problems;

import java.util.*;

class ConstantTimeDS {
    private Map<Integer, Integer> map; // <Element, Index in ArrayList>
    private List<Integer> list;
    private Random random;

    public ConstantTimeDS() {
        map = new HashMap<>();
        list = new ArrayList<>();
        random = new Random();
    }
    
    public boolean insert(int element) {
        if (map.containsKey(element)) {
            return false; // Element already exists
        }
        
        list.add(element);
        map.put(element, list.size() - 1);
        return true;
    }
    
    public boolean delete(int element) {
        if (!map.containsKey(element)) {
            return false; // Element not found
        }
        
        int indexToRemove = map.get(element);
        int lastIndex = list.size() - 1;
        int lastElement = list.get(lastIndex);
        
        // Swap element to delete with the last element
        list.set(indexToRemove, lastElement);
        map.put(lastElement, indexToRemove);
        
        // Remove the element from map and list
        map.remove(element);
        list.remove(lastIndex);
        
        return true;
    }
    
    public boolean search(int element) {
        return map.containsKey(element);
    }
    
    public int getRandom() {
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }
}

public class ConstantTimeDSMain {
    public static void main(String[] args) {
        ConstantTimeDS ds = new ConstantTimeDS();
        
        ds.insert(1);
        ds.insert(2);
        ds.insert(3);
        
        System.out.println("Initial Data Structure:");
        System.out.println("Search 2: " + ds.search(2)); // true
        System.out.println("Search 4: " + ds.search(4)); // false
        System.out.println("Random Element: " + ds.getRandom()); // Random element
        
        ds.delete(2);
        System.out.println("\nData Structure after deleting 2:");
        System.out.println("Search 2: " + ds.search(2)); // false
        System.out.println("Random Element: " + ds.getRandom()); // Random element
        
        ds.insert(4);
        System.out.println("\nData Structure after inserting 4:");
        System.out.println("Search 4: " + ds.search(4)); // true
        System.out.println("Random Element: " + ds.getRandom()); // Random element
    }
}
