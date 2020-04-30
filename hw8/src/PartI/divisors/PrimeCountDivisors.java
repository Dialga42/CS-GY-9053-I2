package PartI.divisors;

import java.util.AbstractMap;
import java.util.Map;

public class PrimeCountDivisors {
    private static Prime p;

    public static void setPrime(Prime prime) {
        p = prime;
    }

    public static int countDivisors(long val) {
        int divisorCount = 1;
        //test if val is a prime number itself
        if (p.getPrimeList().contains(val))
            return 0;
        for (long i : p.getPrimeList()) {
            int tmp = 0;
            //counts the number of each prime factor
            while (val % i == 0) {
                ++tmp;
                val /= i;
            }
            //using multiplication
            divisorCount *= tmp + 1;
            if (val == 1)
                break;
        }
        //exclude 1 and val
        return divisorCount - 2;
    }

    public static Map.Entry<Long, Long> maxDivisors(long from, long to) {

        long maxDivisors = 0;
        long numFound = 0;
        long numberTested = 0;
        for (long num = from; num < to; num++) {
            int divisors = countDivisors(num);
            if (divisors >= maxDivisors) {
                maxDivisors = divisors;
                numFound = num;
            }
            numberTested++;
            if (numberTested == 1000) {
                System.out.print('.');
                numberTested = 0;
            }

        }
        Map.Entry<Long, Long> result = new AbstractMap.SimpleEntry<Long, Long>(numFound, maxDivisors);

        return result;
    }
}
