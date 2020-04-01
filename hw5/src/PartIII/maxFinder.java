package PartIII;

import java.util.*;

public class maxFinder<T extends Number> {
    private Collection<T> list;

    maxFinder() {
        list = new LinkedList<>();
    }

    public void add(T t) {
        list.add(t);
    }


    public <T extends Number> T max() {
        Iterator<T> i = (Iterator<T>) list.iterator();
        T res = i.next();
        while (i.hasNext()) {
            T tmp = i.next();
            if (tmp.doubleValue()>res.doubleValue())
                res = tmp;
        }
        return res;
    }
}
