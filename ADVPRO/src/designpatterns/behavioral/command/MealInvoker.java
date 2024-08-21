package designpatterns.behavioral.command;

/**
MealInvoker is the invoker class.
It contains a reference to the Command to invoke.
Its invoke() method calls the execute() method of the Command.
*/
public class MealInvoker {

      Command command;

      public MealInvoker(Command command) {
         this.command = command;
      }

      public void setCommand(Command command) {
         this.command = command;
      }

      public void invoke() {
         command.execute();
      }
}