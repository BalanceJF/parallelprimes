import java.util.Arrays;

public class PrimeCounter {
    // We want our primes to count on odd numbers, and just already include 2 in the calculation.
    // This ensures no need to check even numbers at any point.
    private long currentNum = 3;
    private long sum = 2;
    private long primesFound = 1;

    // Store the top 10 primes in a list, sorting them later to make my life easier
    private long[] topPrimes = new long[10];

    public synchronized long getAndIncrement() {
        long temp = currentNum;
        currentNum = temp + 2;
        return temp;
    }

    public synchronized void addPrime(long num) {
        primesFound += 1;
        sum += num;
        // Keep a sorted array that we add to the bottom of 
        if (num > 99999000 && num > topPrimes[0]) {
            topPrimes[0] = num;
            Arrays.sort(topPrimes);
        }
    }

    public long getSum() {
        return sum;
    }

    public long getPrimesFound() {
        return primesFound;
    }

    public long[] getTopPrimes() {
        return topPrimes;
    }
}
