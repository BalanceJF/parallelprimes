import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.Thread;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

public class ParallelPrimes extends Thread {
    private static final long maxNum = 100000000;
    private static final int numThreads = 1;
    private PrimeCounter counter;
    private CountDownLatch latch;

    public ParallelPrimes(PrimeCounter counter, CountDownLatch latch) {
        this.counter = counter;
        this.latch = latch;
    }

    public void run() {
        primePrint(this.counter);
        this.latch.countDown();
    }

    public static void main(String [] args) throws InterruptedException, FileNotFoundException {
        FileOutputStream out = new FileOutputStream("primes.txt");
        PrintStream outPrint = new PrintStream(out);
        PrimeCounter counter = new PrimeCounter();

        // Let's a go
        Instant startTime = Instant.now();

        ParallelPrimes [] primeCheckers = new ParallelPrimes[numThreads];
        // Latch to keep track of threads
        CountDownLatch latch = new CountDownLatch(numThreads);
        for (int i = 0; i < numThreads; i++) {
            primeCheckers[i] = new ParallelPrimes(counter, latch);
        }
        for (int i = 0; i < numThreads; i++) {
            primeCheckers[i].start();
        }

        // Stop here until threads finish
        latch.await();

        // Stop and check time!
        Instant endTime = Instant.now();
        double totalTime = Duration.between(startTime, endTime).toMillis() / 1000.0;

        // Print results 
        outPrint.println(totalTime + "s " + counter.getPrimesFound() + " " + counter.getSum());
        long[] topPrimes = counter.getTopPrimes();
        Arrays.sort(topPrimes);
        // no specific formatting given for list, so I used default
        outPrint.println(Arrays.toString(topPrimes));

        // VS Code told me it was sad that I never closed this stream
        outPrint.close();
    }

    private static void primePrint(PrimeCounter counter) {
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
