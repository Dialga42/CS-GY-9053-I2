package Question1Inheritance.edu.nyu.cs9053.midterm.hierarchy;

public class Curler extends WinterSportPlayer {
    private String trouserPattern;

    public Curler(String name, int age, String trouserPattern) {
        super(name, age);
        this.trouserPattern = trouserPattern;
    }

    public String getTrouserPattern() {
        return trouserPattern;
    }

    public void setTrouserPattern(String trouserPattern) {
        this.trouserPattern = trouserPattern;
    }

    public boolean equals(Object o) {
        if (o instanceof Curler) {
            return super.equals(o) && this.trouserPattern.equals(((Curler) o).getTrouserPattern());
        }
        return false;
    }
}
