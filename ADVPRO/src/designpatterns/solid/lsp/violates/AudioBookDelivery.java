package designpatterns.solid.lsp.violates;

public class AudioBookDelivery extends BookDelivery {

     @Override
     void getDeliveryLocations() {

           /* can't be implemented since

            * Audio book doesn't have

            * a physical location. */

     }

}