package Design_Datastructure.java8.optional;

import java.util.Optional;

public class OptionalExamples {

    // Method to get the length of a string, using Optional to avoid NullPointerException
    public static int getLength(String input) {
        return Optional.ofNullable(input).map(String::length).orElse(0); // Returns 0 if input is null
    }

    // Method to find the first character of a string
    public static Optional<Character> getFirstCharacter(String input) {
        return Optional.ofNullable(input).filter(s -> !s.isEmpty()).map(s -> s.charAt(0)); // Returns Optional.empty if input is null or empty
    }

    // Method to concatenate two strings, returning Optional to handle null cases
    public static Optional<String> concatenate(String s1, String s2) {
        return Optional.ofNullable(s1).flatMap(str1 -> Optional.ofNullable(s2).map(str2 -> str1 + str2)); // Returns Optional.empty if either is null
    }

    // Method to convert a string to uppercase, returning Optional to handle null cases
    public static Optional<String> toUpperCase(String input) {
        return Optional.ofNullable(input).map(String::toUpperCase); // Returns Optional.empty if input is null
    }

    // Method to check if a string contains a specific substring, using Optional
    public static boolean containsSubstring(String input, String substring) {
        return Optional.ofNullable(input).map(s -> s.contains(substring)).orElse(false); // Returns false if input is null
    }

    // Method to find the maximum of two integers, handling null cases with Optional
    public static Optional<Integer> findMax(Integer a, Integer b) {
        return Optional.ofNullable(a).flatMap(valA -> Optional.ofNullable(b).map(valB -> Math.max(valA, valB))); // Returns Optional.empty if either is null
    }

    // Method to get a default value if the input is null
    public static String getDefaultIfNull(String input, String defaultValue) {
        return Optional.ofNullable(input).orElse(defaultValue); // Returns defaultValue if input is null
    }

    // Method to execute a Consumer action if the input is not null
    public static void executeIfPresent(String input) {
        Optional.ofNullable(input).ifPresent(System.out::println); // Prints input if not null
    }

    // Method to provide a fallback action if input is null
    public static void executeWithFallback(String input, Runnable fallback) {
        Optional<String> optionalInput = Optional.ofNullable(input);
        if (optionalInput.isPresent()) {
            System.out.println(optionalInput.get());
        } else {
            fallback.run();
        } // Prints input if not null, otherwise runs fallback
    }

    // Method to handle optional objects and return a complex object
    public static Optional<String> complexOperation(String input1, String input2) {
        return concatenate(input1, input2).flatMap(OptionalExamples::toUpperCase).map(s -> "Result: " + s); // Combines operations and handles nulls seamlessly
    }

    // Main method to demonstrate all techniques
    public static void main(String[] args) {
        // Using getLength
        System.out.println("Length: " + getLength("Hello"));

        // Using getFirstCharacter
        getFirstCharacter("Hello").ifPresent(c -> System.out.println("First Character: " + c));

        // Using concatenate
        concatenate("Hello", "World").ifPresent(System.out::println);

        // Using toUpperCase
        toUpperCase("hello").ifPresent(System.out::println);

        // Using containsSubstring
        System.out.println("Contains 'ell': " + containsSubstring("Hello", "ell"));

        // Using findMax
        findMax(5, 10).ifPresent(max -> System.out.println("Max: " + max));

        // Using getDefaultIfNull
        System.out.println("Default: " + getDefaultIfNull(null, "DefaultValue"));

        // Using executeIfPresent
        executeIfPresent("This will be printed");

        // Using executeWithFallback
        executeWithFallback(null, () -> System.out.println("Fallback executed"));

        // Using complexOperation
        complexOperation("Hello", " World").ifPresent(System.out::println);
    }
}
