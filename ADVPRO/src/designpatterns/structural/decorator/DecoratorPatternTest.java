package designpatterns.structural.decorator;

public class DecoratorPatternTest {
/*

The primary intent of the Decorator Design Pattern is to attach additional responsibilities to an
object dynamically.

The Decorator Pattern facilitates us when we need to extend the functionality of
an object dynamically without having to change the original class source or using inheritance.
This is accomplished by creating an object wrapper referred to as a Decorator around the actual object.


We use inheritance or composition to extend the behavior of an object, but this is done at compile time
and it’s applicable to all the instances of the class. We can’t add any new functionality or
remove any existing behavior at runtime – this is when the Decorator pattern comes into the picture.

 */
       public static void main(String[] args) {

          Icecream icecream = new HoneyDecorator(new NuttyDecorator(new SimpleIcecream()));
          System.out.println(icecream.makeIcecream());
       }
}