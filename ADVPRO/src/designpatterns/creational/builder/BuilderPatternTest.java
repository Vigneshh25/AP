package designpatterns.creational.builder;

public class BuilderPatternTest {

    public static void main(String[] args) {

        //Creating object using Builder pattern in java
        Cake whiteCake = new Cake.Builder().sugar(1).butter(0.5).milk(0.5).build();

        //Cake is ready to eat  :lol:
        System.out.println(whiteCake);
    }
}