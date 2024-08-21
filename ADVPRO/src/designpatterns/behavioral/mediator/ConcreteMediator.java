package designpatterns.behavioral.mediator;

public class ConcreteMediator implements IMediator {

      ColleagueA talk;
      ColleagueB fight;

      @Override
      public void talk() {
      System.out.println("Mediator is talking");
      //let the talk colleague do some stuff
      }

      @Override
      public void fight() {
         System.out.println("Mediator is fighting");
      //let the fight colleague do some stuff
      }

      @Override
      public void registerA(ColleagueA a) {
         this.talk=a;
      }

      @Override
      public void registerB(ColleagueB b) {
         this.fight=b;
      }
}