package designpatterns.solid.openclosed.violates;

// Violates OCP: You have to modify the Shape class every time a new shape is added
class Shape {
    public double calculateArea(String shapeType, double... params) {
        switch (shapeType) {
            case "Circle":
                return Math.PI * params[0] * params[0];
            case "Rectangle":
                return params[0] * params[1];
            // Every time a new shape is added, this method needs to be modified
            default:
                return 0;
        }
    }
}
