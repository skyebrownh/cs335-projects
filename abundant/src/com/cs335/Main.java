package com.cs335;

public class Main {

    public static void main(String[] args) {
        abundant();
    }

    static void abundant() {
        System.out.println("Odd abundant numbers less than 10,000:");
        for (int i = 1; i < 10_000; i += 2) {
            if (isAbundant(i))
                System.out.println(i);
        }
    }

    private static boolean isAbundant(int n) {
        int sum = 0;
        for (int i = 1; i < n; i++) {
            if (n % i == 0)
                sum += i;
        }

        return sum > n;
    }
}
