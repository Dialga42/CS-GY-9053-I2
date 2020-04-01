package PartI;

public class Circle1 extends GeometricObject1 {
    private double radius;

    public Circle1(double r) {
        super();
        radius = r;
    }

    public double getRadius() {
        return radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }
}


