public class Counter {
    private long value = 2;
    private long sum = 0;

    public synchronized long getAndIncrement() {
        long temp = value;
        value = temp + 1;
        return temp;
    }

    public synchronized void addToSum(long num) {
        sum += num;
    }

    public synchronized long getSum() {
        return sum;
    }
}
