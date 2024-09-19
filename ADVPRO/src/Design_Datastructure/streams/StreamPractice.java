package Design_Datastructure.streams;

import cricbuzz.player.Person;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamPractice {

    public static void main(String[] args) {
        int[] nums = {23, 4, 2, 2, 423, 534, 534, -75};
        System.out.println(findSecondLargest(nums));

        String[] fruits = {"apple", "orange", "grape", "banana", "guava"};
        System.out.println(findLongestString(fruits));

        List<Person> personList = Arrays.asList(new Person("Ramesh", 20), new Person("Suresh", 30), new Person("Rajesh", 40));
        System.out.println(findAvgAge(personList));

        System.out.println(Arrays.toString(mergeToArrays(nums, nums)));

        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 4, 5, 5);
        List<Integer> list2 = Arrays.asList(3, 4, 5, 6, 7);
        System.out.println(intersectionOfTwoList(list1, list2));
        System.out.println(unionOfTwoList(list1, list2));

        System.out.println(removeDuplicate(list1));
        List<Transaction> transactions = Arrays.asList(
                new Transaction("2022-01-01", 100),
                new Transaction("2022-01-01", 200),
                new Transaction("2022-01-02", 300),
                new Transaction("2022-01-02", 400),
                new Transaction("2022-01-03", 500)
        );
        System.out.println(findSumOfTransactionOfEachDay(transactions));

        System.out.println(findKthSmallestInArray(nums,3));

        System.out.println(findFreqOfWords(fruits));

        System.out.println(findFreqOfCharacters(fruits));

    }

    private static Map<String,Long> findFreqOfWords(String[] fruits) {
        return Arrays.stream(fruits).collect(Collectors.groupingBy(word -> word,Collectors.counting()));
    }

    public static void sortByKeyStreamAPI(HashMap<Integer, String> map) {
        // Using streams to sort and collect the result into a LinkedHashMap
        Map<Integer, String> sortedMap = map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) // Sort by key
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, // Merge function in case of duplicate keys
                        LinkedHashMap::new // Preserve insertion order
                ));

        sortedMap.forEach((key, value) -> System.out.println(key + " -> " + value));

        String input = "java streams are powerful and flexible";

        // Convert the string to a character stream and count frequencies
        Character mostFrequent = input.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> c != ' ') // Ignore spaces
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

    }

    public static void findNonFirstNonRepeatedChar()
    {
        String input = "wsdsfqdawas";
        Character result = input.chars() // Convert to IntStream
                .mapToObj(c -> (char) c) // Convert each int to Character
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting())) // Count frequency
                .entrySet().stream()
                .filter(entry -> entry.getValue() == 1) // Filter non-repeated
                .map(Map.Entry::getKey) // Get the key (character)
                .findFirst() // Get the first one
                .orElse(null);

        List<String> sentences = Arrays.asList(
                "The quick brown fox jumps over the lazy dog",
                "A journey of a thousand miles begins with a single step",
                "To be or not to be that is the question"
        );
        String longestWord = sentences.stream()
                .flatMap(sentence -> Arrays.stream(sentence.split("\\s+")))
                .max(Comparator.comparingInt(String::length))
                .orElse("");
    }

    private static Map<Character, Long> findFreqOfCharacters(String[] fruits) {
        return Arrays.stream(fruits)
                .flatMapToInt(String::chars)            // Convert each word to a stream of characters
                .mapToObj(c -> (char) c)                // Convert int to char
                .collect(Collectors.groupingBy(Function.identity(),  // Group by character
                        Collectors.counting()));        // Count occurrences
    }

    private static int findKthSmallestInArray(int[] nums,int k) {
        return Arrays.stream(nums).distinct().sorted().skip(k-1).findFirst().orElseThrow(() -> new RuntimeException());
    }

    private static Map<String ,Integer> findSumOfTransactionOfEachDay(List<Transaction> transactions) {
        return transactions.stream().collect(Collectors.groupingBy(Transaction::getDate,Collectors.summingInt(Transaction::getFare)));
    }

    private static List<Integer> removeDuplicate(List<Integer> list) {
        return list.stream().distinct().collect(Collectors.toList());
    }

    private static List<Integer> unionOfTwoList(List<Integer> list1, List<Integer> list2) {
        return Stream.concat(list1.stream(),list2.stream()).distinct().sorted().collect(Collectors.toList());
    }

    private static List<Integer> intersectionOfTwoList(List<Integer> list1, List<Integer> list2) {
        return list1.stream().filter(list2::contains).collect(Collectors.toList());
    }

    private static int[] mergeToArrays(int[] nums1, int[] nums2) {
        return Stream.of(nums1,nums2).flatMapToInt(IntStream::of).sorted().toArray();
    }

    private static double findAvgAge(List<Person> personList) {
        return personList.stream().mapToInt(Person::getAge).average().orElse(0);
    }

    private static String findLongestString(String[] fruits) {

        Arrays.stream(fruits)
        .max(Comparator.comparingInt(String::length)
        .thenComparing(Comparator.naturalOrder())) // First compare by length, then by alphabetical order
        .orElseThrow(() -> new RuntimeException("Array must have at least one string"));

        return Arrays.stream(fruits).distinct().min((a, b) -> {
            int lenA = a.length();
            int lenB = b.length();
            if (lenB != lenA) return lenB - lenA;
            return a.compareTo(b);
        }).orElseThrow(() -> new IllegalArgumentException("Fruits is empty"));
    }

    private static int findSecondLargest(int[] nums) {
        return Arrays.stream(nums)
                .distinct()                 // Remove duplicates
                .boxed()                    // Convert int to Integer to use Stream<Integer>
                .sorted((a, b) -> b - a)    // Sort in descending order
                .skip(1)                    // Skip the largest element
                .findFirst()                // Find the next element
                .orElseThrow(() -> new RuntimeException("Array must have at least two distinct elements"));
    }

    static public class Transaction {
        String date;
        int fare;

        public Transaction(String date, int fare) {
            this.date = date;
            this.fare = fare;
        }

        public String getDate() {
            return date;
        }

        public int getFare() {
            return fare;
        }
    }
}
