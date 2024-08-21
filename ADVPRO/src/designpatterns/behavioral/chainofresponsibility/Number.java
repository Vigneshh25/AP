package designpatterns.behavioral.chainofresponsibility;

//This class is the request object.
public class Number {

       private int number;

       public int getNumber() {
          return number;
       }

       public Number(int num){
          number=num;
       }
}