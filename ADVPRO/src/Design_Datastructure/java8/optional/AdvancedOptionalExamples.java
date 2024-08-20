package Design_Datastructure.java8.optional;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdvancedOptionalExamples {

    // Lazy evaluation: Using Supplier to provide a default value lazily
    public static String getValueOrDefault(String input, Supplier<String> defaultValueSupplier) {
        return Optional.ofNullable(input).orElseGet(defaultValueSupplier); // Default value is only computed if needed
    }

    // Composing Optionals: Combining multiple Optionals in a single operation
    public static Optional<String> combineOptionalValues(Optional<String> opt1, Optional<String> opt2, Optional<String> opt3) {
        return Stream.of(opt1, opt2, opt3).flatMap(o -> o.isPresent() ? Stream.of(o.get()) : Stream.empty()).reduce(String::concat); // Combines non-empty Optionals into a single Optional
    }


    // Advanced mapping: Using flatMap with a Function that returns an Optional
    public static Optional<Integer> parseAndAdd(String num1, String num2) {
        return parseInt(num1).flatMap(n1 -> parseInt(num2).map(n2 -> n1 + n2)); // Combines two Optionals after parsing and adds the integers
    }

    // Utility function for parsing integers with Optional
    private static Optional<Integer> parseInt(String number) {
        try {
            return Optional.of(Integer.parseInt(number));
        } catch (NumberFormatException e) {
            return Optional.empty(); // Returns empty Optional if parsing fails
        }
    }

    // Using Optional with streams: Filtering and collecting non-empty Optionals
    // Java 8 alternative for flatMap with Optional
    public static String concatenateNonEmptyValues(Stream<Optional<String>> values) {
        return values.filter(Optional::isPresent) // Filter out empty Optionals
                .map(Optional::get) // Get the value from non-empty Optionals
                .collect(Collectors.joining(", ")); // Joins non-empty values with a comma separator
    }


    // Custom Optional utility: Handling a nullable list and returning the first non-empty value
    public static <T> Optional<T> getFirstNonEmptyValue(Optional<T>... optionals) {
        return Stream.of(optionals).filter(Optional::isPresent).findFirst().orElseThrow(() -> new RuntimeException());
    }

    // Nested Optionals: Handling nested Optionals safely
    public static Optional<String> getNestedValue(Optional<Optional<String>> nestedOptional) {
        return nestedOptional.flatMap(Function.identity()); // Flattens nested Optionals into a single Optional
    }

    // Working with Optional in complex scenarios: Combining streams with Optionals
    public static Optional<Integer> findMaxValueFromStream(Stream<Optional<Integer>> optionalStream) {
        return optionalStream.flatMap(o -> o.map(Stream::of).orElseGet(Stream::empty)).max(Integer::compare); // Finds the maximum value from a stream of Optionals
    }

    // Optional and Exception handling: Wrapping a potentially throwing operation
    public static Optional<String> wrapWithOptional(Supplier<String> operation) {
        try {
            return Optional.ofNullable(operation.get());
        } catch (Exception e) {
            return Optional.empty(); // Returns empty Optional if an exception is thrown
        }
    }

    // Advanced example: Complex function composition using Optionals
    public static Optional<String> processAndCombine(Optional<String> opt1, Optional<String> opt2, Function<String, String> processor) {
        return combineOptionalValues(opt1.map(processor), opt2.map(processor), Optional.empty()).map(String::toUpperCase); // Processes, combines, and converts the result to uppercase
    }

    // Main method to demonstrate advanced techniques
    public static void main(String[] args) {
        // Lazy evaluation example
        System.out.println("Lazy Default: " + getValueOrDefault(null, () -> "Computed Default"));

        // Composing Optionals
        Optional<String> combined = combineOptionalValues(Optional.of("Hello"), Optional.empty(), Optional.of("World"));
        combined.ifPresent(System.out::println);

        // Parsing and adding numbers
        parseAndAdd("10", "20").ifPresent(sum -> System.out.println("Sum: " + sum));

        // Using streams with Optionals
        String concatenated = concatenateNonEmptyValues(Stream.of(Optional.of("A"), Optional.empty(), Optional.of("B")));
        System.out.println("Concatenated: " + concatenated);

        // Custom utility for first non-empty value
        Optional<String> firstNonEmpty = getFirstNonEmptyValue(Optional.empty(), Optional.of("First"), Optional.of("Second"));
        firstNonEmpty.ifPresent(System.out::println);

        // Nested Optionals
        Optional<String> nestedValue = getNestedValue(Optional.of(Optional.of("Nested Value")));
        nestedValue.ifPresent(System.out::println);

        // Finding max value from a stream of Optionals
        Optional<Integer> maxValue = findMaxValueFromStream(Stream.of(Optional.of(10), Optional.of(20), Optional.empty()));
        maxValue.ifPresent(val -> System.out.println("Max Value: " + val));

        // Exception handling with Optional
        Optional<String> wrappedValue = wrapWithOptional(() -> {
            if (Math.random() > 0.5) throw new RuntimeException("Error occurred!");
            return "Success";
        });

        if (wrappedValue.isPresent()) {
            System.out.println(wrappedValue.get());
        } else {
            System.out.println("Operation failed");
        }

        // Complex function composition
        Optional<String> processedResult = processAndCombine(Optional.of("input1"), Optional.of("input2"), String::trim);
        processedResult.ifPresent(System.out::println);
    }
}
