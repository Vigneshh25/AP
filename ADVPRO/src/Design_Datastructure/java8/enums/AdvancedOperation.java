package Design_Datastructure.java8.enums;

import java.util.EnumSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum AdvancedOperation {

    // Enum constants with custom fields and constructors
    ADDITION("+", (a, b) -> a + b),
    SUBTRACTION("-", (a, b) -> a - b),
    MULTIPLICATION("*", (a, b) -> a * b),
    DIVISION("/", (a, b) -> {
        if (b == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return a / b;
    }),
    MODULUS("%", (a, b) -> a % b),
    POWER("^", Math::pow);

    // Fields for each enum constant
    private final String symbol;
    private final Operation operation;

    // Constructor for the enum
    AdvancedOperation(String symbol, Operation operation) {
        this.symbol = symbol;
        this.operation = operation;
    }

    // Getter for symbol
    public String getSymbol() {
        return symbol;
    }

    // Perform the operation
    public double apply(double a, double b) {
        return operation.apply(a, b);
    }

    // Implementing an interface within an enum
    @FunctionalInterface
    private interface Operation {
        double apply(double a, double b);
    }

    // Static methods and advanced operations
    // Mapping symbols to operations
    private static final Map<String, AdvancedOperation> SYMBOL_TO_OPERATION =
            EnumSet.allOf(AdvancedOperation.class)
                    .stream()
                    .collect(Collectors.toMap(AdvancedOperation::getSymbol, Function.identity()));

    // Get an operation by symbol
    public static Optional<AdvancedOperation> getOperationBySymbol(String symbol) {
        return Optional.ofNullable(SYMBOL_TO_OPERATION.get(symbol));
    }

    // Print all operations
    public static void printAllOperations() {
        EnumSet.allOf(AdvancedOperation.class).forEach(op ->
            System.out.printf("Operation: %s, Symbol: %s%n", op, op.getSymbol()));
    }

    // Demonstrating the usage of EnumSet
    public static double applyAll(double a, double b, Set<AdvancedOperation> operations) {
        return operations.stream()
                         .mapToDouble(op -> op.apply(a, b))
                         .sum(); // Example: summing up all operations
    }

    public static void main(String[] args) {
        // Basic operation
        double result = AdvancedOperation.ADDITION.apply(3, 4);
        System.out.println("3 + 4 = " + result);

        // Fetching operation by symbol
        AdvancedOperation.getOperationBySymbol("/")
                .ifPresent(op -> System.out.println("5 / 2 = " + op.apply(5, 2)));

        // Using EnumSet for performing multiple operations
        EnumSet<AdvancedOperation> basicOperations = EnumSet.of(ADDITION, SUBTRACTION, MULTIPLICATION);
        double combinedResult = applyAll(10, 2, basicOperations);
        System.out.println("Combined result of ADD, SUB, MUL on 10 and 2: " + combinedResult);

        // Print all operations
        printAllOperations();

        // Handling exception during operation (e.g., division by zero)
        try {
            double unsafeResult = AdvancedOperation.DIVISION.apply(10, 0);
            System.out.println("10 / 0 = " + unsafeResult);
        } catch (ArithmeticException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
