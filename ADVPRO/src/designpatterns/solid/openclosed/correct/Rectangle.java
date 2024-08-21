package designpatterns.solid.openclosed.correct;

public class Rectangle implements Shape {

     Double length;
     Double width;

     public Double calculateArea() {

           return length * width;
     }
}
