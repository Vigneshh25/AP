package designpatterns.creational.abstractfactory;

import designpatterns.creational.factory.POCO;

public class PocoMobileFactory extends MobileFactory {

      POCO createLenovoMobile(){
        return new POCO();
      }
}