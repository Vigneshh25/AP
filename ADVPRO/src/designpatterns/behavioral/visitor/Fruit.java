package designpatterns.behavioral.visitor;

public class Fruit implements ShoppingCartElement {

    private final int pricePerKg;
    private final int weight;
    private final String name;

    public Fruit(int priceKg, int wt, String name) {
        this.pricePerKg = priceKg;
        this.weight = wt;
        this.name = name;
    }

    public int getPricePerKg() {
        return pricePerKg;
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    @Override
    public int accept(ShoppingCartVisitor visitor) {
        return visitor.visit(this);
    }
}