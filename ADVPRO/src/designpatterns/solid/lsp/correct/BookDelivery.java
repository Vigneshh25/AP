package designpatterns.solid.lsp.correct;

public class BookDelivery {

     String title;
     Integer userID;

}


 class OfflineDelivery extends BookDelivery {

     void getDeliveryLocations() {

     }
}


 class OnlineDelivery extends BookDelivery {

     void getSoftwareOptions() {

     }
}