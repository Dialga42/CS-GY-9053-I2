package Question1Inheritance.edu.nyu.cs9053.midterm.hierarchy;

public class Test {
    public static void main(String[] args) {
        Skier p1 = new Skier("p1", 1, 1),
                s1p1 = new CrossCountrySkier("s1p1", 11, 11, "s1p1"),
                s2p1 = new MogulSkier("s2p1", 21, 21, 21);

        Sledder p2 = new Sledder("p2", 2, "p2"),
                s1p2 = new Bobsledder("s1p2", 12, "s1p2", "s1p2"),
                s2p2 = new Luger("s2p2", 22, "s2p2", "s2p2");

        Curler p3 = new Curler("p3", 3, "p3");

        IceSkater p4 = new IceSkater("p4", 4, 4),
                s1p4 = new FigureSkater("s1p4", 14, 14, 14),
                s2p4 = new SpeedSkater("s2p4", 24, 24, 24);

        System.out.println("Same class with same value: " + p1.equals(new Skier("p1", 1, 1)));
        System.out.println("Same class: " + p3.equals(p3));
        System.out.println("Sibling subclasses with same parameters and same value: " +
                s1p4.equals(new SpeedSkater("s1p4", 14, 14, 14)));
        System.out.println("Subclass and super class: " + s1p2.equals(p2));
        System.out.println("Different classes: " + s1p1.equals(s2p2));
        System.out.println("Different classes with same value: " +
                s2p1.equals(new SpeedSkater("s2p1", 21, 21, 21)));
    }
}
