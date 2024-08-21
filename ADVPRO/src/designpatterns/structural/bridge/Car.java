package designpatterns.structural.bridge;

/**
* Refine abstraction 1 in Bridge pattern
*/
public class Car extends Vehicle {

       public Car(VehicleType type1, VehicleType type2) {
          super(type1, type2);
       }

       @Override
       public void purchase() {
          System.out.print("Car");
          type1.book();
          type2.book();
       }
}