package Question1Inheritance.edu.nyu.cs9053.midterm.hierarchy;

public class Bobsledder extends Sledder {
    private String clothesColor;

    public Bobsledder(String name, int age, String sledColor, String clothesColor) {
        super(name, age, sledColor);
        this.clothesColor = clothesColor;
    }

    public String getClothesColor() {
        return clothesColor;
    }

    public void setClothesColor(String clothesColor) {
        this.clothesColor = clothesColor;
    }

    public boolean equals(Object o) {
        if (o instanceof Bobsledder) {
            return super.equals(o) && this.clothesColor.equals(((Bobsledder) o).getClothesColor());
        }
        return false;
    }
}
