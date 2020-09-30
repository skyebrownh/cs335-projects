# Sieve

"A prime number is any integer greater than one that is evenly divisible only by itself and 1. The Sieve of Eratosthenes is a method of finding prime numbers."

*The Method:*
- Create a primitive boolean array with all element initially set to _true_ (0 and 1 are false)
- Array elements with prime indices remain true
- All others will be set to false
- Starting on index 2 until the end of the array
    - If index is prime, loop through the remainder of the array and set to false every element whose index is a multiple of the current index that is prime
        - _Ex. 2 is prime so set false 4, 6, 8, 10, etc._
    - Continue with next prime index

This program uses an array of 10,000 elements to compute the "sieve" of prime numbers and displays the 10 prime numbers closest to (but less than) 10,000.

