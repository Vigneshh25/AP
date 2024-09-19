package designpatterns.structural.composite;

public class CompositePatternTest {

/*
*
* The Composite Pattern allows you to compose objects into a tree structure to represent
* the part-whole hierarchy. It means you can create a tree of objects that is made of different parts,
* but that can be treated as a whole one big thing.
*
* It allows the client to treat individual objects and composites (objects made up of other objects)
* uniformly.
*
* Operations on a composite object (e.g., add, remove, display) propagate through the entire hierarchy,
* making it easy to manage the whole structure.
*
* When you need to treat individual and composite objects uniformly: A common use case is when you
* have a collection of objects and want to perform the same operations on both individual and
* grouped objects.
*
* Base Component – Base component is the interface for all objects in the composition.
* Client program uses base component to work with the objects in the composition.
* Moreover, it can be an interface or an abstract class with some methods common to all the objects.

Leaf – Defines the behavior for the elements in the composition. It is the building block for
* the composition and implements base component. Although, it doesn’t have references to other Components.

Composite – It consists of leaf elements and implements the operations in base component.
*
* */
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