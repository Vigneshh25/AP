package designpatterns.creational.builder;


/*
    * It is used to create object that involve complex construction process
    * It is used to build object that can be configured step by step
    * It is commonly used to construct immutable objects where all parameters set at construction time
*/
public class BuilderPatternTest {

    public static void main(String[] args) {

        //Creating object using Builder pattern in java
        Cake whiteCake = new Cake.Builder().sugar(1).butter(0.5).milk(0.5).build();

        //Cake is ready to eat  :lol:
        System.out.println(whiteCake);
    }
}