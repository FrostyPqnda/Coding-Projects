import java.util.Arrays;

/**
 * Java program that finds the index position of a number in a
 * Fibonacci sequence.
 * 
 * The Fibonacci sequence is a sequence of numbers.
 * The next number is found by adding the two numbers before it.
 * F(n) = F(n - 1) + F(n - 2) 
 */

public class FibonacciSequenceIndex
{
    public static void main(String[] args)
    {
        // Creates an array with a length of [int max] that will store the sequence of Fibonacci numbers
        int max = 15;
        int[] sequence = new int[max];

        // Creates the first 2 Fibonacci sequence elements
        sequence[0] = 0;
        sequence[1] = 1;

        //create the Fibonacci sequence and store it in int[] sequence
        for(int i = 2; i < max; i++)
            sequence[i] = sequence[i - 1] + sequence[i - 2];

        //print the Fibonacci sequence numbers
        System.out.println("Fibonacci sequence up to " + max + " terms:");
        for(int val = 0; val < max; val++)
            System.out.print(sequence[val] + " ");

        System.out.println("\nIndex position of 55 is: " + findIndex(sequence, 55));
    }

    /**
     * Method findIndex finds the index of an array 
     * and return it.
     */
    public static int findIndex (int[] arr, int n) 
    {
        int index = Arrays.binarySearch(arr, n); // Uses the built-in binarySearch method to find the index of an array
        return (index < 0) ? -1 : index; // Compact if statement that checks if index < 0 is either -1 or the index
    }
}
