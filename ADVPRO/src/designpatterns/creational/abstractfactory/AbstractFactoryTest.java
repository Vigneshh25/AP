package designpatterns.creational.abstractfactory;

import designpatterns.creational.factory.POCO;

public class AbstractFactoryTest {

    public static void main(String[] args) {
        MobileFactory factory = new MobileFactory();
        PocoMobileFactory lmf = (PocoMobileFactory) factory.createMobile("POCO");
        POCO ln = lmf.createLenovoMobile();
        ln.setPrice();
    }
}