package PartI.divisors;

import java.util.*;

public class Prime {
    //This is a prime list beginning from 2
    private Set<Long> list;

    Prime(long max) {
        list = new LinkedHashSet<>();
        int tmp[] = new int[(int) max + 1];
        for (long i = 2; i <= max; i++) {
            if (tmp[(int) i] == 0)
                list.add(i);
            for (long j = i * 2; j <= max; j += i)
                tmp[(int) j] = 1;
        }
    }

    public Set<Long> getPrimeList() {
        return list;
    }

    public static void main(String[] args) {
        Prime p = new Prime(200000);
        for (long i : p.getPrimeList())
            System.out.print(i + " ");
        System.out.println();
        PrimeCountDivisors.setPrime(p);
        System.out.println(PrimeCountDivisors.maxDivisors(100000, 200000));
        System.out.println(CountDivisors.countDivisors(196560));
        System.out.println(CountDivisors.countDivisors(13));
    }
}
