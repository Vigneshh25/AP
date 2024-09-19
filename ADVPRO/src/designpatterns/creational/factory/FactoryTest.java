package designpatterns.creational.factory;

/*
     * It is a creational design pattern when we a superclass with multiple subclass based on the
       input we need to return one of the subclass
     * more over we can apply single pattern to the factory class or make factory method as static
     * use when you want to abstract the process of creating object from the client
*/
public class FactoryTest {
    public static void main(String[] args) {
        Mobile apple = MobileFactory.createMobile("Apple");
        

    }
}
