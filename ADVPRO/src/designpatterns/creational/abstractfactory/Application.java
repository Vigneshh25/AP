package designpatterns.creational.abstractfactory;

/*
    * Normal factory can be used to create set of related objects, An abstract factory returns factories.
    * Hence an abstract factory is used to return factories that can be use to create set of related object

* */
// Client that uses the abstract factory
public class Application {
    private Button button;
    private CheckBox checkBox;

    public Application(GUIFactory factory) {
        button = factory.createButton();
        checkBox = factory.createCheckBox();
    }

    public void render() {
        button.paint();
        checkBox.check();
    }

    public static void main(String[] args) {
        // Choose the operating system dynamically
        String os = "Windows"; // Change this to "Mac" to use Mac components

        GUIFactory factory;

        if (os.equalsIgnoreCase("Windows")) {
            factory = new WindowsFactory();
        } else {
            factory = new MacFactory();
        }

        // Create and render the application with the chosen OS style
        Application app = new Application(factory);
        app.render();
    }
}
