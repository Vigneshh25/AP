package designpatterns.solid.openclosed.correct;

public class AreaCalculator {

     public Double calculateShapeArea(Shape shape) {

           return shape.calculateArea();
     }
}