package PartI;

public abstract class GeometricObject1 implements Comparable<GeometricObject1> {
    public abstract double getArea();

    public static <E extends Comparable<E>> E max(E o1, E o2) {
        return o1.compareTo(o2) >= 0 ? o1 : o2;
    }

    public int compareTo(GeometricObject1 o) {
        return Double.compare(this.getArea(), o.getArea());
    }
}
