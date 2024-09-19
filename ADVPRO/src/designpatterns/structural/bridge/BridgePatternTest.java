package designpatterns.structural.bridge;

public class BridgePatternTest {
/*
* A Bridge Pattern says that just "decouple the abstraction from the implementation so that
* the two can vary independently".
*
* The Adapter Design Pattern helps it two unrelated classes to work together.
* But the Bridge Design Pattern decouples the abstraction and implementation by creating
* two different hierarchies.
*
* This pattern is particularly helpful in systems where the number of possible combinations between
* abstractions and their implementations is large. By bridging them, you reduce complexity and
*    improve flexibility.
* */
      public static void main(String[] args) {

         Vehicle vehicle1= new Car(new NewVehicle(),new OldVehicle());
         vehicle1.purchase();

         Bike vehicle2 = new Bike(new NewVehicle(),new OldVehicle());
         vehicle2.purchase();
      }
}