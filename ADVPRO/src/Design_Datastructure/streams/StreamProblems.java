package Design_Datastructure.streams;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
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
