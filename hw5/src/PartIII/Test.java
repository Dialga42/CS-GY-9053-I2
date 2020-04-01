package PartIII;

import PartII.Complex;

public class Test {
    public static void main(String[] args) {
        maxFinder<Complex> g = new maxFinder<>();
        g.add(new Complex(1,2));
        g.add(new Complex(3,4));
        g.add(new Complex(5,6));
        System.out.println(g.max().toString());

        maxFinder<Integer> i=new maxFinder<>();
        i.add(1);
        i.add(2);
        i.add(3);
        System.out.println(i.max());

        maxFinder<Number> n=new maxFinder<>();
        n.add(1);
        n.add(2.333);
        n.add(new Complex(4,5));
        System.out.println(n.max());

        ComparableMaxFinder<Integer> c=new ComparableMaxFinder<>();
        i.add(1);
        i.add(2);
        i.add(3);
        System.out.println(i.max());
    }
}
