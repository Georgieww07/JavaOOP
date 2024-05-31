package Polymorphism.Shapes_02;

public class Rectangle extends Shape {
    private Double height;
    private Double width;

    public Rectangle(Double height, Double width) {
        this.height = height;
        this.width = width;
    }

    @Override
    protected double calculatePerimeter() {
        return 2 * (this.height + this.width);
    }

    @Override
    protected double calculateArea() {
        return this.height * this.width;
    }

    public Double getHeight() {
        return height;
    }

    public Double getWidth() {
        return width;
    }
}
