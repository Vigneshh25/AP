package designpatterns.solid.lsp.violates;

// Base class
class Bird {
    public void fly() {
        System.out.println("Bird is flying");
    }
}

// Subclass that adheres to LSP
class Sparrow extends Bird {
    @Override
    public void fly() {
        System.out.println("Sparrow is flying");
    }
}

// Subclass that violates LSP
class Penguin extends Bird {
    @Override
    public void fly() {
        // Penguins can’t fly, so this method is incorrect in this context
        throw new UnsupportedOperationException("Penguins can't fly");
    }
}

// Main class to test violation of LSP
public class Main {
    public static void main(String[] args) {
        Bird sparrow = new Sparrow();
        Bird penguin = new Penguin();
        
        sparrow.fly(); // Outputs: Sparrow is flying
        
        try {
            penguin.fly(); // Throws an exception: Penguins can't fly
        } catch (UnsupportedOperationException e) {
            System.out.println(e.getMessage()); // Outputs: Penguins can't fly
        }
    }
}
