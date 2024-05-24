package Problems.set16;

import java.util.*;

class FamilyTree {
    // Define a method to build the family tree from the list of parent-child pairs
    public static Map<String, List<String>> buildFamilyTree(List<String[]> pairs) {
        Map<String, List<String>> tree = new HashMap<>();
        // Iterate through the pairs and add each child to their parent's list of children
        for (String[] pair : pairs) {
            String child = pair[0];
            String parent = pair[1];
            List<String> children = tree.get(parent);
            if (children == null) {
                children = new LinkedList<>();
                tree.put(parent, children);
            }
            children.add(child);
        }
        return tree;
    }

    // Define a method to count the number of children at a given level for a given person
    public static int countChildrenAtLevel(Map<String, List<String>> tree, String name, int level) {
        // Use a breadth-first search to find the person at the given level
        LinkedList<String> queue = new LinkedList<>();
        queue.add(name);
        int currentLevel = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                if (currentLevel == level) {
                    return size;
                }
                List<String> children = tree.get(current);
                if (children != null) {
                    queue.addAll(children);
                }
            }
            currentLevel++;
        }
        return 0;
    }

    public static void main(String[] args) {
        List<String[]> pairs = Arrays.asList(
                new String[] {"Ram", "Syam"},
                new String[] {"Akil", "Syam"},
                new String[] {"Nikil", "Ram"},
                new String[] {"Subhash", "Ram"},
                new String[] {"Karthik", "Akil"}
        );
        Map<String, List<String>> tree = FamilyTree.buildFamilyTree(pairs);
        int level = 2;
        String name = "Syam";
        int count = FamilyTree.countChildrenAtLevel(tree, name, level);
        System.out.println(count);

    }
}

