package Question1Inheritance.edu.nyu.cs9053.midterm.hierarchy;

public class MogulSkier extends Skier {
    private int trouserSize;

    public void setTrouserSize(int trouserSize) {
        this.trouserSize = trouserSize;
    }

    public int getTrouserSize() {
        return trouserSize;
    }

    public MogulSkier(String name, int age, int skiLength, int trouserSize) {
        super(name, age, trouserSize);
        this.trouserSize = trouserSize;
    }

    public boolean equals(Object o) {
        if (o instanceof MogulSkier) {
            return super.equals(o) && this.trouserSize == ((MogulSkier) o).trouserSize;
        }
        return false;
    }
}
