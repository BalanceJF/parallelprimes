import java.lang.Thread;
import java.util.concurrent.CountDownLatch;

public class ParallelPrimes extends Thread {
    private static final long maxNum = 100000000;
    private static final int numThreads = 8;
    private Counter counter;
    private CountDownLatch latch;

    public ParallelPrimes(Counter counter, CountDownLatch latch) {
        this.counter = counter;
        this.latch = latch;
    }

    public void run() {
        primePrint(this.counter);
        this.latch.countDown();
    }

    public static void main(String [] args) throws InterruptedException {
        Counter counter = new Counter();
        ParallelPrimes [] primeCheckers = new ParallelPrimes[numThreads];
        CountDownLatch latch = new CountDownLatch(numThreads);
        for (int i = 0; i < numThreads; i++) {
            primeCheckers[i] = new ParallelPrimes(counter, latch);
        }
        for (int i = 0; i < numThreads; i++) {
            primeCheckers[i].start();
        }

        latch.await();
        System.out.println(counter.getSum());
        System.out.println(counter.getCount());
    }

    private static void primePrint(Counter counter) {
        long i = counter.getAndIncrement();
        while (i < maxNum) {
            if (isPrime(i)) {
                counter.addPrime(i);
            }
            i = counter.getAndIncrement();
        }
    }

    // Checks all odd divisors up to the square root of the number (odd because we only calculate on odd numbers)
    private static boolean isPrime(long num) {
        for (int i = 3; i * i <= num; i += 2) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
