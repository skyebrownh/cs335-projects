package com.cs335;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        sieve();
    }

    static void sieve() {
        boolean[] primes = new boolean[10_000];
        Arrays.fill(primes, Boolean.TRUE);
        // 0 and 1 are not prime
        primes[0] = false;
        primes[1] = false;

        for (int i = 2; i < 10_000; i++) {
            if (primes[i] && isPrime(i)) {
                // set all indices that are multiples of this prime number to false
                for (int j = i + 1; j < 10_000; j++) {
                    if (j % i == 0)
                        primes[j] = false;
                }
            }
        }

        // print last 10 primes
        int numLeft = 10;
        for (int j = primes.length - 1; j >= 0; j--) {
            if (numLeft > 0 && primes[j]) {
                System.out.println(j);
                numLeft--;
            }
        }
    }

    private static boolean isPrime(int n) {
        if (n <= 1)
            return false;

        for (int i = 2; i < n; i++) {
            if (n % i == 0)
                return false;
        }

        return true;
    }
}
