package designpatterns.structural.bridge;

/**
* abstraction in Bridge pattern
* */
public abstract class Vehicle {
      protected VehicleType type1;
      protected VehicleType type2;

      public Vehicle(VehicleType type1, VehicleType type2) {
         this.type1 = type1;
         this.type2 = type2;
      }

      abstract public void purchase();
}