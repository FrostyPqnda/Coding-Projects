using System.Collections.Generic;
using System;

namespace DimensionalArraySorter
{
    class DimensionalArraySorter
    {
        static void Main(string[] args)
        {
            int[,] matrix = GenerateRandom2DArray(10);
            Console.WriteLine("Unsorted");            
            PrintMatrix(matrix, matrix.GetLength(0));

            SortMatrix(matrix, matrix.GetLength(0));
            Console.WriteLine("\nSorted");
            PrintMatrix(matrix, matrix.GetLength(0));

            Console.ReadKey();  
        }

        // Sorts the given matrix [Inspired by GeeksForGeeks Matrix Sort - https://www.geeksforgeeks.org/sort-given-matrix/]
        static void SortMatrix(int[,] curr, int length)
        {
            int[] temp = new int[length * length]; // Create a temporary array with a length of @param length^2
            int count = 0; // Counter variable for the temporary array

            // Copy elements of the multi-dimensional arrays one by one into the temporary array
            for(int row = 0; row < length; row++)
                for(int col = 0; col < length; col++)
                    temp[count++] = curr[row, col];
        
            MergeSort(temp, temp.Length); // Calls the MergeSort function to sort the temporary array

            count = 0; // Resets the counter variable back to zero
            // Copy elements of the temporary array one by one into the multi-dimensional array
            for(int row = 0; row < length; row++)
                for(int col = 0; col < length; col++)
                    curr[row, col] = temp[count++];
        }

        // Print all the elements of the multi-dimensional array
        static void PrintMatrix(int[,] curr, int length)
        {
            for(int row = 0; row < length; row++) {
                for(int col = 0; col < length; col++) {
                    Console.Write(curr[row, col] + "\t");
                }
                Console.WriteLine();
            }
        }

        // Sorts the arrays using the Merge Sort algorithm
        static void MergeSort(int[] curr, int length)
        {
            // Base case - Array has length of 1
            if(length < 2)
                return;

            int midPoint = length / 2; // Find middle
            int[] leftArr = new int[midPoint]; // Array starting from midpoint
            int[] rightArr = new int[length - midPoint]; // Array starting from first index to midpoint

            // Populate the first half into left
            for(int i = 0; i < midPoint; i++)
                leftArr[i] = curr[i];
            
            // Populate the second half into right
            for(int i = midPoint; i < length; i++)
                rightArr[i - midPoint] = curr[i];
            
            MergeSort(leftArr, midPoint); // Recursive call to sort the left array
            MergeSort(rightArr, length - midPoint); // Recursive call to sort the right array
            Merge(curr, leftArr, rightArr); // Merge the left and right arrays

        }

        // Combines the splitted arrays for the MergeSort method to use
        static void Merge(int[] curr, int[] left, int[] right)
        {
            int x = 0, y = 0, z = 0; // counter variables for the arrays

            /* Loop through the left and right arrays and look for the lowest index value
            in the array (the lowest value overall). */
            while(x < left.Length && y < right.Length)
                /* Compares the left value and right value, takes the lowest one, and adds it to the
                current array. Then, it will increment the index for the array the value came from */
                if(left[x] <= right[y])
                    curr[z++] = left[x++];
                else    
                    curr[z++] = right[y++];

            // Places the remaining values into the current array.
            while(x < left.Length)
                curr[z++] = left[x++];
            
            while(y < right.Length)
                curr[z++] = right[y++];
        }

        // Creates a randomized 2d array with a row and column size of @param number
        static int[,] GenerateRandom2DArray(int number)
        {
            int[,] arr = new int[number, number];
            Random rng = new Random();

            for(int row = 0; row < number; row++)
                for(int col = 0; col < number; col++)
                    arr[row, col] = rng.Next(0, 10001);

            return arr;
        }
    }
}
