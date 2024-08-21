package designpatterns.creational.abstractfactory;

public interface IMobileFactory {

    IMobileFactory createMobile(String type);
}