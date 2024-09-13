package designpatterns.behavioral.command;

public class CommandPatternTest {

/*
    The Command Pattern is a behavioral object design pattern. In the command pattern, a
    Command interface declares a method for executing a particular action. Concrete Command
    classes implement the execute () method of the Command interface, and this execute () method
    invokes the appropriate action method of a Receiver class that the Concrete Command class contains.
*/

      public static void main(String[] args) {

         Lunch lunch = new Lunch(); // receiver
         Command lunchCommand = new LunchCommand(lunch); // concrete command

         Dinner dinner = new Dinner(); // receiver
         Command dinnerCommand = new DinnerCommand(dinner); // concrete command

         MealInvoker mealInvoker = new MealInvoker(lunchCommand); // invoker
         mealInvoker.invoke();

         mealInvoker.setCommand(dinnerCommand);
         mealInvoker.invoke();
      }
}