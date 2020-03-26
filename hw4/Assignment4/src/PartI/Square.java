package PartI;

public class Square extends Rectangle {
    public Square() {
    }

    /**
     * A constructor that initializes the length and width to the given value side
     *
     * @param side
     */
    public Square(double side) {
        super(side, side);
    }

    /**
     * A constructor that initializes the length and width to the given value side and initializes color and filled
     *
     * @param color
     * @param filled
     * @param side
     */
    public Square(String color, boolean filled, double side) {
        super(color, filled, side, side);
    }

    /**
     * getSide
     *
     * @return side
     */
    public double getSide() {
        return getWidth();
    }

    /**
     * setSide: set length and width
     *
     * @param side
     */
    public void setSide(double side) {
        super.setLength(side);
        super.setWidth(side);
    }

    /**
     * setWidth: setSide
     *
     * @param side
     */
    public void setWidth(double side) {
        setSide(side);
    }

    /**
     * setLength: setSide
     *
     * @param side
     */
    public void setLength(double side) {
        setSide(side);
    }

    /**
     * A override method to return "A Square with side=xxx, which is a subclass of yyy",
     * where yyy is the output of the toString() method from the superclass
     *
     * @return
     */
    public String toString() {
        return "A Square with side=" + getSide() + ", which is a subclass of " + super.toString();
    }
}
