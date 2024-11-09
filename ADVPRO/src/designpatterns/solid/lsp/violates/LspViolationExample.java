package designpatterns.solid.lsp.violates;

// Superclass representing a generic Bird
class Bird {
    // Method that represents the flying capability of birds
    public void fly() {
        System.out.println("Bird is flying");
    }
}

// Subclass representing a Sparrow, which can fly
class Sparrow extends Bird {
    @Override
    public void fly() {
        System.out.println("Sparrow is flying");
    }
}

// Subclass representing a Penguin, which cannot fly
class Penguin extends Bird {
    @Override
    public void fly() {
        // Violates LSP; Penguins cannot fly
        throw new UnsupportedOperationException("Penguins cannot fly");
    }
}

// Main class to demonstrate LSP violation
public class LspViolationExample {
    public static void main(String[] args) {
        Bird sparrow = new Sparrow();
        sparrow.fly(); // Outputs: Sparrow is flying

        Bird penguin = new Penguin();
        penguin.fly(); // Throws an exception, violating LSP
    }
}
