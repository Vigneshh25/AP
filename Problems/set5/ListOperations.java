package Problems.set5;


    import java.util.HashSet;
import java.util.Set;

    public class ListOperations {
        public static void main(String[] args) {
            Set<Integer> list1 = new HashSet<>();
            list1.add(1);
            list1.add(3);
            list1.add(4);
            list1.add(5);
            list1.add(6);
            list1.add(8);
            list1.add(9);

            Set<Integer> list2 = new HashSet<>();
            list2.add(1);
            list2.add(5);
            list2.add(8);
            list2.add(9);
            list2.add(2);

            // find the union of the two lists
            Set<Integer> union = new HashSet<>(list1);
            union.addAll(list2);

            // find the intersection of the two lists
            Set<Integer> intersection = new HashSet<>(list1);
            intersection.retainAll(list2);

            // find the except of the two lists
            Set<Integer> except = new HashSet<>(list1);
            except.removeAll(list2);

            System.out.println("Union: " + union);
            System.out.println("Intersection: " + intersection);
            System.out.println("Except: " + except);
            for(int list :intersection)
            {
                if(intersection.contains(list))
                    System.out.println(list);
            }
        }
    }

