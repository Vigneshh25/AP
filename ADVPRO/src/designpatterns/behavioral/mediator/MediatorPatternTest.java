package designpatterns.behavioral.mediator;

public class MediatorPatternTest {

      public static void main(String[] args) {
         IMediator mediator = new ConcreteMediator();

         ColleagueA talkColleague= new ColleagueA(mediator);
         talkColleague.doSomething();

         ColleagueB fightColleague = new ColleagueB(mediator);
         fightColleague.doSomething();
      }
}