package designpatterns.solid.lsp.correct;

// Interface representing the flying behavior
interface Flyable {
    // Method for flying
    void fly();
}

// Abstract superclass representing a generic Bird
abstract class Bird {
    // Abstract method to make sound
    public abstract void makeSound();
}

// Subclass representing a Sparrow, which can fly and make a sound
class Sparrow extends Bird implements Flyable {
    @Override
    public void makeSound() {
        System.out.println("Sparrow chirps");
    }

    @Override
    public void fly() {
        System.out.println("Sparrow is flying");
    }
}

// Subclass representing a Penguin, which cannot fly but can make
class Penguin extends Bird {
    @Override
    public void makeSound() {
        System.out.println("Penguin honks");
    }

    // Note: No fly() method implemented, adhering to LSP
}

class LspCorrectExample {
    public static void main(String[] args) {
        // Instantiate a Sparrow object
        Bird sparrow = new Sparrow();
        sparrow.makeSound(); // Outputs: Sparrow chirps
        ((Flyable) sparrow).fly(); // Outputs: Sparrow is flying

        // Instantiate a Penguin object
        Bird penguin = new Penguin();
        penguin.makeSound(); // Outputs: Penguin honks

        // Note: No fly() method call on penguin, adhering to LSP
    }
}
