package PartI;

public class Rectangle extends Shape {
    private double width, length;

    /**
     * The no-arg constructor initializes the width and length to 1.0
     */
    public Rectangle() {
        width = length = 1;
    }

    /**
     * A constructor that initializes the length and width to the given values
     *
     * @param width
     * @param length
     */
    public Rectangle(double width, double length) {
        this.width = width;
        this.length = length;
    }

    /**
     * A constructor that initializes the color, filled, length and width to the given values
     *
     * @param color
     * @param filled
     * @param width
     * @param length
     */
    public Rectangle(String color, boolean filled, double width, double length) {
        super(color, filled);
        this.width = width;
        this.length = length;
    }

    /**
     * getWidth
     *
     * @return width
     */
    public double getWidth() {
        return width;
    }

    /**
     * setWidth
     *
     * @param width
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * getLength
     *
     * @return length
     */
    public double getLength() {
        return length;
    }

    /**
     * setLength
     *
     * @param length
     */
    public void setLength(double length) {
        this.length = length;
    }

    /**
     * A method to compute area
     *
     * @return area
     */
    public double getArea() {
        return width * length;
    }

    /**
     * A method to compute perimeter
     *
     * @return perimeter
     */
    public double getPerimeter() {
        return 2 * (width + length);
    }

    /**
     * An override method return "A Rectangle with width=xxx and length=zzz,
     * which is a subclass of yyy", where yyy is the output of the toString() method from the superclass.
     *
     * @return string
     */
    public String toString() {
        return "A Rectangle with width=" + width + " and length=" + length + ", which is a subclass of " + super.toString();
    }
}
