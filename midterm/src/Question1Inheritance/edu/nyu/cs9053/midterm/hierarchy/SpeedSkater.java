package Question1Inheritance.edu.nyu.cs9053.midterm.hierarchy;

public class SpeedSkater extends IceSkater {
    private int maxSpeed;

    public SpeedSkater(String name, int age, int skateSize, int maxSpeed) {
        super(name, age, skateSize);
        this.maxSpeed = maxSpeed;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public boolean equals(Object o) {
        if (o instanceof SpeedSkater) {
            return super.equals(o) && this.maxSpeed == ((SpeedSkater) o).getMaxSpeed();
        }
        return false;
    }
}
