package Question1Inheritance.edu.nyu.cs9053.midterm.hierarchy;

public abstract class WinterSportPlayer {
    private String name;
    private int age;

    public WinterSportPlayer() {
    }

    public WinterSportPlayer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass())
            return false;
        WinterSportPlayer t = (WinterSportPlayer) o;
        return this.age == t.getAge() && this.name.equals(t.getName());
    }
}
