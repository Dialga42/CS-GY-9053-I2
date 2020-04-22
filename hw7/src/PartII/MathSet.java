package PartII;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MathSet<E> extends HashSet<E> {

    public Set<E> intersection(Set<E> s2) {
        Set<E> res = new MathSet<>();
        for (E i : s2) {
            if (this.contains(i))
                res.add(i);
        }
        return res;
    }

    public Set<E> union(Set<E> s2) {
        Set<E> res = new MathSet<>();
        for (E i : s2) {
            res.add(i);
        }
        for (E i : this) {
            if (!res.contains(i))
                res.add(i);
        }
        return res;
    }

    public <T> Set<Pair<E, T>> cartesianProduct(Set<T> s2) {
        Set<Pair<E,T>> res=new MathSet<>();
        for (E i:this)
        {
            for (T j:s2)
            {
                res.add(new Pair<>(i,j));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        // create two MathSet objects of the same type.
        // See if union and intersection works.
        Set<Integer> a=new MathSet<>();
        a.add(2);
        a.add(4);
        a.add(6);
        a.add(8);
        a.add(10);
        a.add(12);
        Set<Integer> b=new MathSet<>();
        b.add(3);
        b.add(6);
        b.add(9);
        b.add(12);
        System.out.println("a is " + a);
        System.out.println("b is " + b);
        System.out.println("intersection of a and b is " + ((MathSet)a).intersection(b));
        System.out.println("union of a and b is " + ((MathSet)a).union(b));
        // create another MathSet object of a different type
        // calculate the cartesian product of this set with one of the
        // above sets
        Set<Integer> c=new MathSet<>();
        c.add(1);
        c.add(2);
        c.add(3);
        Set<String> d=new HashSet<>();
        d.add("a");
        d.add("b");
        d.add("c");
        System.out.println("c is " + c);
        System.out.println("d is " + d);
        System.out.println("cartesianProduct of c and d is " + ((MathSet)c).union(d));
    }
}
