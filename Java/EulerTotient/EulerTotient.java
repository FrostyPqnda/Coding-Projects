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
        int res = n;
        for(int p = 2; p * p <= n; p++) {
            if (n % p == 0) {
                while (n % p == 0) {
                    n /= p;
                }
                res -= res / p;
            }
        }

        if (n > 1) {
            res -= res / n;
        }

        return res;
    }
}