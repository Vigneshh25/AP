package designpatterns.behavioral.visitor;

public interface ShoppingCartElement {

    int accept(ShoppingCartVisitor visitor);
}