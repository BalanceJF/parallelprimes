import java.lang.Thread;

public class ParallelPrimes extends Thread {
    private static final long maxNum = 100000000;
    private static final int numThreads = 8;
    private Counter counter;

    public ParallelPrimes(Counter counter) {
        this.counter = counter;
    }

    public void run() {
        primePrint(this.counter);
    }

    public static void main(String [] args) {
        Counter counter = new Counter();
        ParallelPrimes [] primeCheckers = new ParallelPrimes[numThreads];
        for (int i = 0; i < numThreads; i++) {
            primeCheckers[i] = new ParallelPrimes(counter);
        }
        for (int i = 0; i < numThreads; i++) {
            primeCheckers[i].start();
        }

        System.out.println(counter.getSum());
    }

    private static void primePrint(Counter counter) {
        long i = counter.getAndIncrement();
        while (i < maxNum) {
            if (isPrime(i)) {
                counter.addPrime(i);
            }
            i = counter.getAndIncrement();
        }
        System.out.println(counter.getSum());
        System.out.println(counter.getCount());
    }

    private static boolean isPrime(long num) {
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
