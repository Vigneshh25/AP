package designpatterns.creational.abstractfactory;

// Abstract factory
interface GUIFactory {
    Button createButton();
    CheckBox createCheckBox();
}
