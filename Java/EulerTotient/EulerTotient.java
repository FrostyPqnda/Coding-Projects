/**
 * A Java program that calculates Euler's Totient, which is the number of 
 * integers from [1, n] that are relatively co-prime to n -> GCD = 1.
 * 
 * Formula:
 * n times the product of 1 - 1 / all prime factors of n
 * Φ(n) = n * ∏(1 - 1/pᵢ) where pᵢ is the prime factors of n
 */
import java.util.ArrayList;
import java.util.Scanner;

class EulerTotient {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int n = scan.nextInt();
        int e = phi(n);
        System.out.println("The number of integers relatively co-prime to " + n + " = " + e);
        scan.close();
    }

    // Calculate Euler's Totient -> the number of integers relatively
    // co-prime to n -> GCD(a, n) = 1
    static int phi(int n) {
        double k = 1;
        ArrayList<Integer> primes = primeFactor(n);
        filter(primes);

        for(int prime : primes) {
            k *= (1 - (1.0 / prime));
        }

        return (int)(n * k);
    }

    // Removes all duplicates from the prime factor list.
    // Only works assuming that the list is already sorted.
    static void filter(ArrayList<Integer> arrList) {
        for(int i = arrList.size() - 1; i > 0; i--) {
            if(arrList.get(i) == arrList.get(i - 1)) {
                arrList.remove(i - 1);
            }
        }
        return;
    }

    // Returns ArrayList of the prime factor of n
    static ArrayList<Integer> primeFactor(int n) {
        if(n < 1) {
            return null;
        }

        ArrayList<Integer> primeList = new ArrayList<Integer>();
        for(int i = 2; i <= n; i++) {
            while(n % i == 0) {
                primeList.add(i);
                n /= i;
            }
        }

        return primeList;
    }
}