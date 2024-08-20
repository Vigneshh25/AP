package Design_Datastructure.streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamProblems {

    public static void main(String[] args) {

        System.out.println(fibonacci(5));

        List<Integer> numbers = Arrays.asList(5, 2, 1, 3, 4);
        System.out.println(findMedian(numbers));

        List<String> strings = Arrays.asList("flower", "flow", "flight");
        System.out.println(findLongestCommonPrefix(strings));

        System.out.println(findMaxProductOfTwoNum(numbers));


        List<String> words = Arrays.asList("listen", "silent", "hello", "world", "night", "thing");
        System.out.println(findAnagram(words));

        List<Integer> number = Arrays.asList(2, 4, 6, 8, 10);
        int target = 12;

        System.out.println(twoSum(number, target));

        List<Integer> list = Arrays.asList(1, 2, 3, 2, 4, 5, 6, 4, 7, 8, 9, 9);
        System.out.println(findNonDuplicateNum(list));

        List<String> languages = Arrays.asList("Java", "Python", "C++", "Go");
        System.out.println(concatWords(languages));

        List<String> animals = Arrays.asList("elephant", "cat", "dinosaur", "giraffe");
        System.out.println(longestString(animals));

        List<Integer> num = Arrays.asList(2, 3, 4, 5);
        System.out.println(productOfNumUsingReduce(num));



    }

    private static int productOfNumUsingReduce(List<Integer> num) {
        return num.stream().reduce(1,(a,b) -> a*b);
    }

    private static String longestString(List<String> animals) {
        return animals.stream().reduce((str1, str2) -> str1.length() >= str2.length() ? str1 : str2).orElse("");
    }

    private static String concatWords(List<String> languages) {
        return languages.stream().reduce((a, b) -> a + "," + b).orElse("");
    }

    private static List<Integer> findNonDuplicateNum(List<Integer> list) {
        return list.stream().collect(Collectors.groupingBy( Function.identity(), Collectors.counting())) // Group by number and count occurrences
                .entrySet().stream().filter(entry -> entry.getValue() == 1) // Keep only entries with a count of 1
                .map(Map.Entry::getKey) // Extract the number
                .collect(Collectors.toList()); // Collect the result into a list
    }

    private static Set<String> twoSum(List<Integer> number, int target) {
        return number.stream().flatMap(num1 -> number.stream().map(num2 -> num1 + num2 == target ? "(" + num1 + "," + num2 + ")" : "")).filter(s -> !s.isEmpty()).collect(Collectors.toSet());
    }

    private static Collection<List<String>> findAnagram(List<String> words) {
        return words.stream().collect(Collectors.groupingBy(word -> {
            char[] charArray = word.toCharArray();
            Arrays.sort(charArray);
            return new String(charArray);
        })).values();
    }

    private static int findMaxProductOfTwoNum(List<Integer> numbers) {
        return numbers.stream().flatMapToInt(i -> numbers.stream().filter(k -> k != i).mapToInt(b -> b * i)).max().orElse(-1);
    }

    private static String findLongestCommonPrefix(List<String> strings) {
        return strings.stream().reduce((str1, str2) -> {
            int len = Math.min(str2.length(), str1.length());
            int i = 0;
            while (i < len && str1.charAt(i) == str2.charAt(i)) {
                i++;
            }
            return str2.substring(0, i);
        }).orElse(" ");
    }

    private static OptionalDouble findMedian(List<Integer> numbers) {
        int size = numbers.size();

        // Sort the list
        List<Integer> sortedNumbers = numbers.stream().sorted().collect(Collectors.toList());
        if (size == 0) return OptionalDouble.empty();
        if (size % 2 == 1) return OptionalDouble.of(sortedNumbers.get(size / 2));

        return OptionalDouble.of((sortedNumbers.get((size / 2) - 1) + sortedNumbers.get(size / 2)) / 2.0);

    }

    private static int fibonacci(int n) {
        return Stream.iterate(new int[]{0, 1}, fib -> new int[]{fib[1], fib[0] + fib[1]}).limit(n)                  // Limit the stream to the first 'n' Fibonacci numbers
                .skip(n - 1)               // Skip the first 'n-1' elements to get the 'n-th' element
                .map(fib -> fib[0])        // Map to the first element of the array (the Fibonacci number)
                .findFirst()               // Get the first element after skipping
                .orElse(-1);               // Return -1 if something goes wrong (shouldn't happen with positive 'n')
    }
}
