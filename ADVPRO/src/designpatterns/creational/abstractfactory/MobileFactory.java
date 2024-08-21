package designpatterns.creational.abstractfactory;

public class MobileFactory implements IMobileFactory {

    @Override
    public IMobileFactory createMobile(String type) {

        switch (type) {
            case "POCO":
                return new PocoMobileFactory();
            case "Samsung":
                return new SamsungMobileFactory();
            default:
                return null;
        }

    }
}