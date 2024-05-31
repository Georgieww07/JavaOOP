package Polymorphism.Shapes_02;

public abstract class Shape {
    private Double perimeter;
    private Double area;

    protected abstract double calculatePerimeter();
    protected abstract double calculateArea();

    public Double getPerimeter() {
        return this.calculatePerimeter();
    }

    public Double getArea() {
        return this.calculateArea();
    }
}
