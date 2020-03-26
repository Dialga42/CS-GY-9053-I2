package PartI;

public class Circle extends Shape {
    private double radius;

    /**
     * A no-arg constructor initializes the radius to 1.0
     */
    public Circle() {
        radius = 1.0;
    }

    /**
     * A constructor initializes the radius to a value
     *
     * @param radius
     */
    public Circle(double radius) {
        this.radius = radius;
    }

    /**
     * A constructor that initializes the color, filled and radius to the given values
     *
     * @param color
     * @param filled
     * @param radius
     */
    public Circle(String color, boolean filled, double radius) {
        super(color, filled);
        this.radius = radius;
    }

    /**
     * getRadius
     *
     * @return radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * setRadius
     *
     * @param radius
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * A method to compute area
     *
     * @return area
     */
    public double getArea() {
        return Math.PI * radius * radius;
    }

    /**
     * A method to compute perimeter
     *
     * @return perimeter
     */
    public double getPerimeter() {
        return Math.PI * 2 * radius;
    }

    /**
     * An override toString method that return "A Circle with radius=xxx, which is a subclass of yyy",
     * where yyy is the output of the toString() method from the superclass.
     *
     * @return string
     */
    public String toString() {
        return "A Circle with radius=" + radius + ", which is a subclass of " + super.toString();
    }
}
