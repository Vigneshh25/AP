package designpatterns.behavioral.visitor;

/*
*
* The Visitor design pattern introduces a separate object, called the visitor, that encapsulates the
* operation to be performed on a variety of objects. The visitor defines a visit method for each
* object type in the hierarchy, allowing the operation to be implemented in a centralized and
* reusable manner.
*
* When we have to perform an operation on a group of similar kind of Objects, we use Visitor Pattern.
* With the help of visitor pattern, we can move the operational logic from the objects to another class.
* For example, think of a Shopping cart where we can add different type of items (Elements),
* when we click on the checkout button, it calculates the total amount that we need to pay.
* Now, we can have the calculation logic in item classes or we can move out this logic to another
* class using Visitor Pattern. So, using visitor pattern we can move out logics to another class.
*
*
* */


public class VisitorPatternTest {

    public static void main(String[] args) {

        ShoppingCartElement[] items = new ShoppingCartElement[]{new Book(20, "1234"), new Book(100, "5678"), new Fruit(10, 2, "Banana"), new Fruit(5, 5, "Apple")};

        int total = calculatePrice(items);
        System.out.println("Total Cost = " + total);
    }

    private static int calculatePrice(ShoppingCartElement[] items) {

        ShoppingCartVisitor visitor = new ShoppingCartVisitorImpl();
        int sum = 0;
        for (ShoppingCartElement item : items) {
            sum = sum + item.accept(visitor);
        }
        return sum;
    }
}