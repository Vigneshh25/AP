package designpatterns.solid.openclosed.correct;

public class Circle implements Shape {

     public Double radius;

     public Double calculateArea() {

           return (22 / 7) * radius * radius;
     }
}
