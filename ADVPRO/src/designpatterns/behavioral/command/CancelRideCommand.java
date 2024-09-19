package designpatterns.behavioral.command;

// Concrete Command: CancelRideCommand
class CancelRideCommand implements Command {
   private RideService receiver;
   private String passenger;

   public CancelRideCommand(RideService receiver, String passenger) {
       this.receiver = receiver;
       this.passenger = passenger;
   }

   public void execute() {
       receiver.cancelRide(passenger);
   }
}
