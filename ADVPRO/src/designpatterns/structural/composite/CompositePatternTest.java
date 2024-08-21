package designpatterns.structural.composite;

public class CompositePatternTest {

    public static void main(String[] args) {

        Employee emp1 = new Developer("Robert", 10000);
        Employee emp2 = new Developer("David", 15000);
        Employee manager1 = new Manager("Mark", 25000);
        manager1.add(emp1);
        manager1.add(emp2);
        Employee emp3 = new Developer("Mary", 20000);
        Manager generalManager = new Manager("John", 50000);
        generalManager.add(emp3);
        generalManager.add(manager1);
        generalManager.print();

    }
}