package Question1Inheritance.edu.nyu.cs9053.midterm.hierarchy;

public class IceSkater extends WinterSportPlayer {
    private int skateSize;

    public IceSkater(String name, int age, int skateSize) {
        super(name, age);
        this.skateSize = skateSize;
    }

    public double getSkateSize() {
        return skateSize;
    }

    public void setSkateSize(int skateSize) {
        this.skateSize = skateSize;
    }

    public boolean equals(Object o) {
        if (o instanceof IceSkater) {
            return super.equals(o) && this.skateSize == ((IceSkater) o).getSkateSize();
        }
        return false;
    }
}
