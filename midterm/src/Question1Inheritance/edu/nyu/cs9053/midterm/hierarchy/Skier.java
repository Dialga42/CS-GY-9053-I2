package Question1Inheritance.edu.nyu.cs9053.midterm.hierarchy;

public class Skier extends WinterSportPlayer {
    private int skiLength;

    public Skier(String name, int age, int skiLength) {
        super(name, age);
        this.skiLength = skiLength;
    }

    public int getSkiLength() {
        return skiLength;
    }

    public void setSkiLength(int skiLength) {
        this.skiLength = skiLength;
    }

    public boolean equals(Object o) {
        if (o instanceof Skier) {
            return super.equals(o) && this.skiLength == ((Skier) o).getSkiLength();
        }
        return false;
    }
}
