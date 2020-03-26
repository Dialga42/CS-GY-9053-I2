package PartI;

public class Shape {
    private String color;
    private boolean filled;

    /**
     * A no-arg (no-argument) constructor that initializes the color to "green" and filled to true
     */
    public Shape() {
        color = "green";
        filled = true;
    }

    /**
     * a constructor that initializes the color and filled to the given values
     *
     * @param color
     * @param filled
     */
    public Shape(String color, boolean filled) {
        this.color = color;
        this.filled = filled;
    }

    /**
     * getColor
     *
     * @return color
     */
    public String getColor() {
        return color;
    }

    /**
     * setColor
     *
     * @param color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Get if this shape is filled.
     *
     * @return 0 not filled; 1 filled;
     */
    public boolean isFilled() {
        return filled;
    }

    /**
     * Set this shape to filled/ not filled.
     *
     * @param filled 0 not filled; 1 filled;
     */
    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    /**
     * A toString() method that returns "A Shape with color of xxx and filled/Not filled"
     *
     * @return string
     */
    public String toString() {
        return "A Shape with color of " + color + " and " + (filled ? "" : "Not ") + "filled";
    }
}
