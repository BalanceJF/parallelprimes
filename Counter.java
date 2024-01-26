public class Counter {
    // We want our primes to count on odd numbers, and just already include 2 in the calculation.
    // This ensures no need to check even numbers at any point.
    private long value = 3;
    private long sum = 2;
    private long count = 1;

    public synchronized long getAndIncrement() {
        long temp = value;
        value = temp + 2;
        return temp;
    }

    public synchronized void addPrime(long num) {
        count += 1;
        sum += num;
    }

    public synchronized long getSum() {
        return sum;
    }

    public synchronized long getCount() {
        return count;
    }
}
