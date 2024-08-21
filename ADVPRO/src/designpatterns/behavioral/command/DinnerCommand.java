package designpatterns.behavioral.command;

/**
The DinnerCommand is similar to LunchCommand.
It contains a reference to Dinner, a receiver.
Its execute() method invokes the makeDinner() action of the Dinner object.
*/
public class DinnerCommand implements Command {

      Dinner dinner;

      public DinnerCommand(Dinner dinner){
         this.dinner=dinner;
      }

      @Override
      public void execute() {
         dinner.makeDinner();
      }
}