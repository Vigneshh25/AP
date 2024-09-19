package designpatterns.behavioral.command;

class RideRequestInvoker {
   public void processRequest(Command command) {
       command.execute();
   }
}