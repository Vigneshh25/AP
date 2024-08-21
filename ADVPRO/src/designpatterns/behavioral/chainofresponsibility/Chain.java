package designpatterns.behavioral.chainofresponsibility;

//This is the interface that acts as a chain link.
public interface Chain {
      public void setNext(Chain nextInChain);
      public void process(Number request);
}