package PartI;

public class Test {
    public static void testShape() {
        Shape a = new Shape(), b = new Shape("blue", false);
        System.out.println(a.toString());
        System.out.println(a.getColor());
        System.out.println(a.isFilled());
        System.out.println(b.toString());
        b.setColor("black");
        b.setFilled(true);
        System.out.println(b.toString());
    }

    public static void testCircle() {
        Circle a = new Circle(), b = new Circle(2), c = new Circle("yellow", false, 3);
        System.out.println(a.toString());
        System.out.println(b.toString());
        System.out.println(c.toString());
        a.setRadius(42);
        System.out.println(a.getRadius());
        System.out.println(a.getArea());
        System.out.println(a.getPerimeter());
        System.out.println(a.toString());
    }

    public static void testRectangle() {
        Rectangle a = new Rectangle(), b = new Rectangle(2, 3), c = new Rectangle("yellow", false, 3, 4);
        System.out.println(a.toString());
        System.out.println(b.toString());
        System.out.println(c.toString());
        a.setWidth(4);
        a.setLength(5);
        System.out.println(a.getWidth());
        System.out.println(a.getLength());
        System.out.println(a.getArea());
        System.out.println(a.getPerimeter());
        System.out.println(a.toString());
    }

    public static void testSquare() {
        Square a = new Square(), b = new Square(2), c = new Square("yellow", false, 3);
        System.out.println(a.toString());
        System.out.println(b.toString());
        System.out.println(c.toString());
        a.setWidth(4);
        System.out.println(a.getWidth());
        System.out.println(a.getLength());
        System.out.println(a.getSide());
        System.out.println(a.getArea());
        System.out.println(a.getPerimeter());
        System.out.println(a.toString());
    }

    public static void main(String[] args) throws Exception {
        testShape();
        testCircle();
        testRectangle();
        testSquare();
    }
}
