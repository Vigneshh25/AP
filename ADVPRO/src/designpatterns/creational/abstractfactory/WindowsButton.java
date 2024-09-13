package designpatterns.creational.abstractfactory;

// Concrete product: WindowsButton
class WindowsButton implements Button {
    @Override
    public void paint() {
        System.out.println("Rendering a button in a Windows style.");
    }
}

// Concrete product: MacButton
class MacButton implements Button {
    @Override
    public void paint() {
        System.out.println("Rendering a button in a Mac style.");
    }
}

// Concrete product: WindowsCheckBox
class WindowsCheckBox implements CheckBox {
    @Override
    public void check() {
        System.out.println("Checking a checkbox in a Windows style.");
    }
}

// Concrete product: MacCheckBox
class MacCheckBox implements CheckBox {
    @Override
    public void check() {
        System.out.println("Checking a checkbox in a Mac style.");
    }
}
