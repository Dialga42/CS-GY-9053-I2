package Question1Inheritance.edu.nyu.cs9053.midterm.hierarchy;

public class FigureSkater extends IceSkater {
    private double height;

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public FigureSkater(String name, int age, int skateSize, double height) {
        super(name, age, skateSize);
        this.height = height;
    }

    public boolean equals(Object o) {
        if (o instanceof FigureSkater) {
            return super.equals(o) && this.height == ((FigureSkater) o).height;
        }
        return false;
    }
}
