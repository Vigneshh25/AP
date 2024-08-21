package designpatterns.behavioral.command;

/**
LunchCommand implements Command. It contains a reference to Lunch, a receiver.
Its execute() method invokes the appropriate action on the receiver.
*/
public class LunchCommand implements Command {

      Lunch lunch;

      public LunchCommand(Lunch lunch) {
         this.lunch = lunch;
      }

      @Override
      public void execute() {
         lunch.makeLunch();
      }
}