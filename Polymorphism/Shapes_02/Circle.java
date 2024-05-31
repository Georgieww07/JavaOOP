package Polymorphism.Shapes_02;

public class Circle extends Shape {
    private Double radius;

    public Circle(Double radius) {
        this.radius = radius;
    }

    @Override
    protected double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    protected double calculateArea() {
        return Math.PI * Math.pow(this.radius, 2);
    }

    public final Double getRadius() {
        return radius;
    }
}
