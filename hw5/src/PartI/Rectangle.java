package PartI;

public class Rectangle extends GeometricObject1 {
    private double a, b;

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double getLength() {
        return this.a;
    }

    public double getWidth() {
        return this.b;
    }

    public double getArea() {
        return this.a * this.b;
    }
}
