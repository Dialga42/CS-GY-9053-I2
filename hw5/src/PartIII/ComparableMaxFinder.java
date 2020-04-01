package PartIII;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class ComparableMaxFinder<T extends Comparable> {
    private Collection<T> list;

    ComparableMaxFinder() {
        list = new LinkedList<>();
    }

    public void add(T t) {
        list.add(t);
    }

    public <T extends Comparable> T max() {
        Iterator<T> i = (Iterator<T>) list.iterator();
        T res = i.next();
        while (i.hasNext()) {
            T tmp = i.next();
            if (tmp.compareTo(res)>0)
                res = tmp;
        }
        return res;
    }
}

