using System;

namespace FibonacciIndexSearch
{
    class FibonacciSearch
    {
        static void Main(string[] args)
        {
            Console.Title = "Fibonacci Index Search";
            Console.ForegroundColor = ConsoleColor.DarkCyan;

            Console.Write("Enter a length: ");
            int input = Convert.ToInt32(Console.ReadLine());
            
            int[] arr = GenerateFibonacciSeries(input);
            Console.WriteLine("\nThe Fibonacci Sequence of " + input);
            foreach(int item in arr)
                Console.Write(item + " ");
            
            Console.Write("\n\nEnter a number: ");
            int index = Convert.ToInt32(Console.ReadLine());

            Console.WriteLine("\nThe index position of " + index + " is " + FibonacciIndexSearch(arr, index));
            Console.ReadKey();
        }

        // Creates a fibonacci series array
        static int[] GenerateFibonacciSeries(int length)
        {
            // New array with a size of @param length
            int[] fib = new int[length];
            // Fills up the fib array
            for(int i = 2; i < length; i++)
            {
                fib[0] = 0;
                fib[1] = 1;
                fib[i] = fib[i - 1] + fib[i - 2];
            }
            return fib;
        }
        // Returns the index of where target is found in the array
        static int FibonacciIndexSearch(int[] arr, int target)
        {
            // Loops through the entire array to search for the target
            for(int i = 0; i < arr.Length; i++)
            {
                // If the index is equal to the target value, return the position
                // of where it first occurs
                if(arr[i] == target)
                {
                    return i;
                }
            }
            // If the target is not in the array, return -1
            return -1;
        }
    }
}
