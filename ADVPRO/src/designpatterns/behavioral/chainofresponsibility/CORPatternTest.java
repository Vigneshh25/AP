package designpatterns.behavioral.chainofresponsibility;

public class CORPatternTest {

      public static void main(String[] args) {

      //configure Chain of Responsibility
         Chain c1 = new NegativeNumberProcessor();
         Chain c2 = new ZeroProcessor();
         Chain c3 = new PositiveNumberProcessor();
         c1.setNext(c2);
         c2.setNext(c3);

     //calling chain of responsibility
         c1.process(new Number(99));
         c1.process(new Number(-30));
         c1.process(new Number(0));
         c1.process(new Number(100));
      }
}