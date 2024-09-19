package designpatterns.behavioral.command;

// Concrete Command: RideRequestCommand
class RideRequestCommand implements Command {
   private RideService receiver;
   private String passenger;
   private String srcLoc;
   private String destLoc;

   public RideRequestCommand(RideService receiver, String passenger, String srcLoc, String destLoc) {
       this.receiver = receiver;
       this.passenger = passenger;
       this.srcLoc = srcLoc;
       this.destLoc = destLoc;
   }

   public void execute() {
       receiver.requestRide(passenger, srcLoc, destLoc);
   }
}
