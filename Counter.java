public class Counter {
    private long value = 2;
    private long sum = 0;
    private long count = 0;

    public synchronized long getAndIncrement() {
        long temp = value;
        value = temp + 1;
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
