package designpatterns.behavioral.chainofresponsibility;

public class CORPatternTest {

/*
   When there are more than one objects that can handle or fulfill a client request,
   the pattern recommends giving each of these objects a chance to process
   the request in some sequential order.

   The first object in the chain receives the request and decides either to handle the request or to
   pass it on to the next object in the chain. The request flows through all objects in the chain
   one after the other until the request is handled by one of the handlers in the chain or
   the request reaches the end of the chain without getting processed.

   For example we know exception handling better than anybody else We know that we can have
   multiple catch blocks in a try-catch block code. Here every catch block is kind of a
   processor to process that particular exception. So, when any exception occurs in the
   try block it is sent to the first catch block to process. If that catch block is not able
   to process it, it forwards the request to the next object in chain i.e. next catch block.
*/

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