package set35;

import java.util.HashMap;
import java.util.Scanner;

public class Frequnency {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the size of the array:");
            int size = scanner.nextInt();
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) {
                System.out.println("Enter a number:");
                arr[i] = scanner.nextInt();
            }
            scanner.close();

            findFrequencies(arr);
        }

        public static void findFrequencies(int[] arr) {
            HashMap<Integer, Integer> frequencies = new HashMap<>();
            for (int n : arr) {
                if (frequencies.containsKey(n)) {
                    frequencies.put(n, frequencies.get(n) + 1);
                } else {
                    frequencies.put(n, 1);
                }
            }
            for (int n : frequencies.keySet()) {
                System.out.println(n + " appears " + frequencies.get(n) + " times");
            }
        }
    }


