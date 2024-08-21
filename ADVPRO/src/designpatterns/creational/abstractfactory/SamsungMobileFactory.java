package designpatterns.creational.abstractfactory;

import designpatterns.creational.factory.Samsung;

public class SamsungMobileFactory extends MobileFactory {

      Samsung createSamsungMobile(){
         return new Samsung();
      }
}