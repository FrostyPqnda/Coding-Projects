using System;

namespace DimensionalArraySorter
{
    class MatrixSorter
    {
        static void Main(string[] args)
        {   
            // Create an object of the Random2DArray class
            Random2DArray rng = new Random2DArray(); 

            // Ask users for the row length
            Console.Write("Enter the row length: ");
            int rowLength = Convert.ToInt32(Console.ReadLine());
            int row = (rowLength > 1) ? rowLength : 1;

            // Ask users for the column length
            Console.Write("Enter the column length: ");
            int columnLength = Convert.ToInt32(Console.ReadLine());
            int col = (columnLength > 1) ? columnLength : 1;

            // Create a randomized, unsorted 2D array and print it out to the terminal
            int[,] matrix = rng.GenerateRandom2DArray(row, col);
            Console.WriteLine("\nUnsorted:");
            PrintMatrix(matrix);
            
            // Sorts the randomized, unsorted 2D array and print it out to the terminal
            Console.WriteLine("\nSorted:");
            SortMatrix(matrix, row, col);
            PrintMatrix(matrix);
            
            // Prevents the terminal from closing on run 
            Console.ReadKey();  
        }

        // Sorts the given matrix [Inspired by GeeksForGeeks Matrix Sort - https://www.geeksforgeeks.org/sort-given-matrix/]
        static void SortMatrix(int[,] curr, int rowLength, int columnLength)
        {
            int[] arr = new int[rowLength * columnLength]; // Create a temporary array with a length of @param rowLength * columnLength
            int count = 0; // Counter variable for the temporary array

            // Copy elements of the multi-dimensional arrays one by one into the temporary array
            for(int row = 0; row < rowLength; row++)
                for(int col = 0; col < columnLength; col++)
                    arr[count++] = curr[row, col];

            MergeSorter merge = new MergeSorter(); // Create object of the MergeSorter class
            merge.MergeSort(arr, arr.Length); // Calls the MergeSort function to sort the temporary array

            count = 0; // Resets the counter variable back to zero
            // Copy elements of the temporary array one by one into the multi-dimensional array
            for(int row = 0; row < rowLength; row++)
                for(int col = 0; col < columnLength; col++)
                    curr[row, col] = arr[count++];
        }

        // Print all elements of a multi-dimensional array
        static void PrintMatrix(int[,] curr)
        {
            for(int row = 0; row < curr.GetLength(0); row++) {
                for(int col = 0; col < curr.GetLength(1); col++) {
                    Console.Write(curr[row, col] + "\t");
                }
                Console.WriteLine();
            }
        }
    }
}
