package designpatterns.solid.lsp.correct;

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

// Another subclass that adheres to LSP
class Penguin extends Bird {
    @Override
    public void fly() {
        // Penguins can't fly, but they can swim
        System.out.println("Penguins can't fly");
    }
}

// Main class to test adherence to LSP
public class Main {
    public static void main(String[] args) {
        Bird sparrow = new Sparrow();
        Bird penguin = new Penguin();
        
        sparrow.fly(); // Outputs: Sparrow is flying
        penguin.fly(); // Outputs: Penguins can't fly
    }
}
