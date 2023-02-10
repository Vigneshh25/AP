package set4;

public class Main {
    public static void main(String[] args) {
        // create an array of petrol bunks
        PetrolBunk[] petrolBunks = {
                new PetrolBunk("A", 1, 6),
                new PetrolBunk("B", 5, 4),
                new PetrolBunk("C", 3, 2)
        };

        // set the initial petrol in the car and the total distance to the office
        int petrol = 2;
        int distanceToOffice = 8;

        // iterate through the petrol bunks
        for (PetrolBunk bunk : petrolBunks) {
            // calculate the distance to the bunk and the amount of petrol needed
            int distance = bunk.getDistance();
            int petrolNeeded = distance - petrol;

            // if the petrol bunk has enough capacity, fill up the car
            if (petrolNeeded <= bunk.getCapacity()) {
                petrol += petrolNeeded;
            }
            // otherwise, fill up the car as much as possible and set a flag
            // indicating that the car cannot reach the office
            else {
                petrol += bunk.getCapacity();
                System.out.println("Out of petrol");
                break;
            }
        }

        // if the car has enough petrol, print the remaining amount
        if (petrol >= distanceToOffice) {
            System.out.println("Remaining petrol: " + (petrol - distanceToOffice));
        }
    }
}
