package designpatterns.structural.bridge.without;// Without Bridge Pattern



/*Problem:

If we need to add a new color, we need to create new classes for every shape with the new color.
If we need to add a new shape, we need to create new classes for each color
*/
abstract class Shape {
    public abstract void draw();
}

class RedCircle extends Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Red Circle");
    }
}

class BlueCircle extends Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Blue Circle");
    }
}

class RedRectangle extends Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Red Rectangle");
    }
}

class BlueRectangle extends Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Blue Rectangle");
    }
}

public class Main {
    public static void main(String[] args) {
        Shape redCircle = new RedCircle();
        redCircle.draw();

        Shape blueRectangle = new BlueRectangle();
        blueRectangle.draw();
    }
}
