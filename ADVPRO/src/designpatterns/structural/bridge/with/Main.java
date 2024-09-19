package designpatterns.structural.bridge.with;



/*How the Bridge Pattern Works:

Color (Implementor): Red and Blue are concrete implementations of the Color interface.
Shape (Abstraction): Circle and Rectangle are abstracted as shapes.
Shape is "bridged" with Color: The Shape class holds a reference to Color, and both can vary independently.
When a Shape is drawn, the corresponding Color is applied.
*/
// Implementor
interface Color {
    void applyColor();
}

// Concrete Implementors
class Red implements Color {
    @Override
    public void applyColor() {
        System.out.println("Applying Red Color");
    }
}

class Blue implements Color {
    @Override
    public void applyColor() {
        System.out.println("Applying Blue Color");
    }
}

// Abstraction
abstract class Shape {
    protected Color color;

    public Shape(Color color) {
        this.color = color;
    }

    abstract void draw();
}

// Refined Abstraction
class Circle extends Shape {
    public Circle(Color color) {
        super(color);
    }

    @Override
    public void draw() {
        System.out.print("Drawing Circle with ");
        color.applyColor();
    }
}

class Rectangle extends Shape {
    public Rectangle(Color color) {
        super(color);
    }

    @Override
    public void draw() {
        System.out.print("Drawing Rectangle with ");
        color.applyColor();
    }
}

// Main class to test Bridge Pattern
public class Main {
    public static void main(String[] args) {
        Shape redCircle = new Circle(new Red());
        redCircle.draw();

        Shape blueRectangle = new Rectangle(new Blue());
        blueRectangle.draw();
    }
}
