package Taxi.versions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vignesh.V on 24/05/24.
 */
class TaxiService {
    private List<Taxi> taxis;


    public TaxiService(int numTaxis) {
        taxis = new ArrayList<>();
        for (int i = 0; i < numTaxis; i++) {
            taxis.add(new Taxi(i + 1));
        }
    }

    public List<Taxi> getFreeTaxis(int pickupTime, char pickupPoint) {
        List<Taxi> freeTaxis = new ArrayList<>();
        for (Taxi taxi : taxis) {
            if (taxi.isFree(pickupTime, pickupPoint)) {
                freeTaxis.add(taxi);
            }
        }
        return freeTaxis;
    }

    public void displayTaxiDetails() {
        for (Taxi taxi : taxis) {
            taxi.displayDetails();
        }
    }
}
