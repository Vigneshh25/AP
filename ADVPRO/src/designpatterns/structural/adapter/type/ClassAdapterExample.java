package designpatterns.structural.adapter.type;

/*

The Class Adapter pattern uses inheritance to adapt one interface to another. In this approach,
the adapter class extends (inherits from) the class it is adapting (the adaptee) and implements
the target interface. This means the adapter can override methods of the adaptee if needed

Pros:
    It’s simple and easy to use.
    The adapter has access to the Adaptee’s protected members since it inherits from the Adaptee.

Cons:
    Java doesn’t support multiple inheritance, so if the adapter class already extends another class, this approach cannot be used.*/
interface Target {
    void request();
}

class Adaptee {
    public void specificRequest() {
        System.out.println("Adaptee's specificRequest called.");
    }
}

class ClassAdapter extends Adaptee implements Target {
    @Override
    public void request() {
        specificRequest();
    }
}

public class ClassAdapterExample {
    public static void main(String[] args) {
        Target target = new ClassAdapter();
        target.request();
    }
}
