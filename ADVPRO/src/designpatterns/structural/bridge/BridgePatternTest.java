package designpatterns.structural.bridge;

public class BridgePatternTest {
/*
* A Bridge Pattern says that just "decouple the functional abstraction from the implementation so that
* the two can vary independently".
*
* The Adapter Design Pattern helps it two incompatible classes to work together.
* But the Bridge Design Pattern decouples the abstraction and implementation by creating
* two different hierarchies.
* */
      public static void main(String[] args) {

         Vehicle vehicle1= new Car(new NewVehicle(),new OldVehicle());
         vehicle1.purchase();

         Bike vehicle2 = new Bike(new NewVehicle(),new OldVehicle());
         vehicle2.purchase();
      }
}