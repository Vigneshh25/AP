package designpatterns.structural.adapter.type;


/*
 * The Object Adapter pattern uses composition to achieve adaptation. Instead of inheriting from the
 * adaptee, the adapter holds a reference to an instance of the adaptee and delegates method
 * calls to it. This allows for more flexibility, as the adapter can wrap multiple different
 * objects and doesn't require direct inheritance.
 *
 * */

class ObjectAdapter implements Target {
    private final Adaptee adaptee;

    public ObjectAdapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        adaptee.specificRequest();
    }
}

// Client code
public class ObjectAdapterExample {
    public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();
        Target target = new ObjectAdapter(adaptee);
        target.request();
    }
}
