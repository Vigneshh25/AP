package bikerental;

/** * Created by Vignesh.V on 21/06/24. */ // BikeFactory Class
class BikeFactory {
    public static Bike createBike(String id, String name, Size size) {
        return new Bike(id, name, size);
    }
}
