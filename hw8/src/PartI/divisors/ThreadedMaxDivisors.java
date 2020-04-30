package PartI.divisors;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import static java.lang.Long.max;
import static java.lang.Long.min;

public class ThreadedMaxDivisors implements Runnable {

    private long min;
    private long max;
    private Entry<Long, Long> ans;

    public ThreadedMaxDivisors(long min, long max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public void run() {
        ans = PrimeCountDivisors.maxDivisors(min, max);
    }

    public Entry<Long, Long> getAns() {
        return ans;
    }

    public static void main(String[] args) {

        long min = 100_000;
        long max = 200_000;

        Set<Thread> threadSet = new HashSet<Thread>();
        Set<ThreadedMaxDivisors> divisorsSet = new HashSet<ThreadedMaxDivisors>();
        long startTime = System.currentTimeMillis();

        PrimeCountDivisors.setPrime(new Prime(max));

        long len = (long) Math.sqrt(max - min);
        for (long i = min; i <= max; i += len) {
            ThreadedMaxDivisors myThread = new ThreadedMaxDivisors(i, min(i + len - 1, max));
            Thread thr = new Thread(myThread);
            thr.start();
            divisorsSet.add(myThread);
            threadSet.add(thr);
        }

        /* join() tells a thread to wait until it's complete before the rest of the code and proceed.
         * if we do that for all the threads, then then we can get the results of the threads once
         * all of them are done
         */
        for (Thread t : threadSet) {
            try {
                t.join();
                System.out.print("Done");
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        // at this point, all threads have been completed, since we
        // called the "join()" method on all the threads we created,
        // which forces the code to wait until the thread is finished
        // before we continue
        long maxDivisors = 0, numFound = 0;
        for (ThreadedMaxDivisors tmd : divisorsSet) {
            // presumably you've recorded the results of
            // each ThreadedMaxDivisors run. Pick
            // the largest number with the largest number of
            // divisors
            Entry<Long, Long> now = tmd.getAns();
            if (now.getValue() > maxDivisors) {
                maxDivisors = now.getValue();
                numFound = now.getKey();
            } else if (now.getValue() == maxDivisors) {
                numFound = max(now.getKey(), numFound);
            }
        }
        System.out.println("\n" + numFound + "=" + maxDivisors);
        long endTime = System.currentTimeMillis();
        System.out.println("Threaded elapsed time: " + (endTime - startTime));
        startTime = System.currentTimeMillis();
        Entry<Long, Long> e = CountDivisors.maxDivisors(min, max);

        long number = e.getKey();
        long numDivisors = e.getValue();

        System.out.println("\n" + number + ": " + numDivisors);
        endTime = System.currentTimeMillis();

        System.out.println("Non-threaded elapsed time: " + (endTime - startTime));


    }
}
