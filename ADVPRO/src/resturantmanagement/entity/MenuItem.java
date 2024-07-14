package resturantmanagement.entity;

import java.util.Objects;

public class MenuItem {
    public enum Category { STARTER, MAIN_COURSE, DESSERT }
    public enum Type { VEG, NON_VEG }

    private String name;
    private double price;
    private Type type;
    private Category category;

    public MenuItem(String name, double price, Type type, Category category) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Type getType() {
        return type;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", type=" + type +
                ", category=" + category +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MenuItem menuItem = (MenuItem) obj;
        return Double.compare(menuItem.price, price) == 0 &&
                name.equals(menuItem.name) &&
                type == menuItem.type &&
                category == menuItem.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, type, category);
    }
}
