*********************************
# Parallel Primes Readme
*********************************

This program was written for a hypothetical manager as part of a university assignment. The following is a set of instructions on operating the program as part of those requirements:

Hi, boss! I'm so excited to show you how to use this program. In order to run my amazing primes calculator, simply follow these instructions!
1. From the command prompt, cd into the directory where you clone this repository.
2. Type the following commands:

```
javac ParallelPrimes.java PrimeCounter.java
java ParallelPrimes
```

3. Wait about 8 seconds.
4. Your output should appear in the desired primes.txt file!



Summary of approach:
I took 8 threads and had them all use a synchronized counter helper class to keep track between themselves of their progress. A thread would come along, grab a new odd number, and check if this number was prime, advancing the counter forward to the next number to check in the process (thus each thread was getting a unique number to check). They would then take the answer to whether it was prime and add the number's information to the synchronized counter's data. To tell if the number was prime, its divisibility was checked against odd factors up to the square root of the number being checked. 
Side note: We can make the optimization of checking only odd factors since we know any even numbers will not go into our odd-only club. We only have to check up to the square root because any pair of factors that multiply to the desired number must have exactly 1 element that is less than or equal to the root of the number (since a pair of two numbers both less than the square root wouldn't multiply to be big enough to equal the desired number).
I used a latch and the await function to ensure all threads finished, then printed the results.
Finally, as a sanity check, I also checked what happened if I decreased the number of threads used in my program. As stated, 8 threads took about 8 seconds. With 1 thread only it took about 40 seconds, and with 2 it took about 20. This seems to indicate that parallel programming techniques are having a large effect on efficiency.
