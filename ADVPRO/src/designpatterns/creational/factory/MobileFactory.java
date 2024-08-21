package designpatterns.creational.factory;

public class MobileFactory {

    public static Mobile createMobile(String type) {
        switch (type) {
            case "Apple":
                return new Apple();
            case "POCO":
                return new POCO();
            case "Samsung":
                return new Samsung();
            default:
                return null;
        }
    }
}
