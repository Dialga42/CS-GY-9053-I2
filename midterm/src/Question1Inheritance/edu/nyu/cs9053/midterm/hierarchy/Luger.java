package Question1Inheritance.edu.nyu.cs9053.midterm.hierarchy;

public class Luger extends Sledder {
    private String hatColor;

    public Luger(String name, int age, String sledColor, String hatColor) {
        super(name, age, sledColor);
        this.hatColor = hatColor;
    }

    public String getHatColor() {
        return hatColor;
    }

    public void setHatColor(String hatColor) {
        this.hatColor = hatColor;
    }

    public boolean equals(Object o) {
        if (o instanceof Luger) {
            return super.equals(o) && this.hatColor.equals(((Luger) o).getHatColor());
        }
        return false;
    }
}
