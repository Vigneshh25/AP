package designpatterns.creational.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class EagerInitialization {

    /* Eager Initialization
    Below instance is guaranteed to be thread safe.
    Eager Initialization
    Below instance is guaranteed to be thread safe.*/

    private static final EagerInitialization instance = new EagerInitialization();

    private EagerInitialization() {
        System.out.println("Creating Instance....");
        if (instance != null) {
            throw new RuntimeException("Instance Already Created..");
        }
    }

    public static EagerInitialization getInstance() {
        return instance;
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, CloneNotSupportedException {

        EagerInitialization instance1 = EagerInitialization.getInstance();
        EagerInitialization instance2 = EagerInitialization.getInstance();

        System.out.println(Objects.equals(instance1.hashCode(), instance2.hashCode()));

        Class classz = Class.forName("designpatterns.creational.singleton.EagerInitialization");
        Constructor<EagerInitialization> declaredConstructor = classz.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        EagerInitialization newInstance = declaredConstructor.newInstance();
        System.out.println(Objects.equals(instance1.hashCode(), newInstance.hashCode()));


        EagerInitialization instance3 = (EagerInitialization) instance2.clone();
        System.out.println("Hashcode of Object instance3: " + instance3.hashCode());
    }

    protected Object clone() throws CloneNotSupportedException {
        if (instance != null) {
            throw new CloneNotSupportedException("Can't create instance. Please use getInsance() to create it.");
        }
        return super.clone();
    }


}
