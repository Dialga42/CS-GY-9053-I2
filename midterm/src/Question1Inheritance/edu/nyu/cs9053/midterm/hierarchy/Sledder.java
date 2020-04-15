package Question1Inheritance.edu.nyu.cs9053.midterm.hierarchy;

public class Sledder extends WinterSportPlayer {
    private String sledColor;

    public Sledder(String name, int age, String sledColor) {
        super(name, age);
        this.sledColor = sledColor;
    }

    public String getSledColor() {
        return sledColor;
    }

    public void setSledColor(String sledColor) {
        this.sledColor = sledColor;
    }

    public boolean equals(Object o) {
        if (o instanceof Sledder) {
            return super.equals(o) && this.sledColor.equals(((Sledder) o).getSledColor());
        }
        return false;
    }
}
