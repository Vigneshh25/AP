package designpatterns.behavioral.templete;



/*
*
* The Template Method Pattern describes the skeleton of an algorithm in an operation, deferring
* some steps to subclasses. Template Method lets subclasses redefine certain steps of an algorithm
* without changing the algorithm’s structure.
*
* We can use the Template Method pattern in situations when there is an algorithm, some steps of
* which could be implemented in multiple different ways. In such scenarios, the Template Method
* pattern suggests keeping the outline of the algorithm in a separate method referred to as a
* template method inside a class.
*
*
* */
public class TemplateMethodPatternTest {

      public static void main(String[] args) {
         PurchaseOrderTemplate online= new OnlinePurchaseOrder();
         online.processOrder();
         PurchaseOrderTemplate offline= new StorePurchaseOrder();
         offline.processOrder();
      }
}