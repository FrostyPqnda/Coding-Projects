using System;

namespace DimensionalArraySorter
{
    class MatrixSorter : QuickSorter
    {
        static void Main(string[] args)
        {   
            Console.Title = "Matrix Sorter";
            Console.ForegroundColor = ConsoleColor.DarkCyan;

            // Ask users for the row length
            Console.Write("Enter the row length: ");
            int rowLength = Convert.ToInt32(Console.ReadLine()); // Input row
            int row = (rowLength > 1) ? rowLength : 1; // If the inputted row length is less than 1, then the row length is set to 1

            // Ask users for the column length
            Console.Write("Enter the column length: ");
            int columnLength = Convert.ToInt32(Console.ReadLine()); // Input column
            int col = (columnLength > 1) ? columnLength : 1; // If the inputted column length is less than 1, then the row length is set to 1

            // Create a randomized, unsorted 2D array and print it out to the terminal
            int[,] matrix = RandomMatrix(row, col);
            Console.WriteLine("\nUnsorted:");
            PrintMatrix(matrix);
            
            // Sorts the randomized, unsorted 2D array and print it out to the terminal
            Console.WriteLine("\nSorted:");
            SortMatrix(matrix, row, col);
            PrintMatrix(matrix);
            
            // Prevents the terminal from closing on run 
            Console.ReadKey();  
        }

        // Sorts the given matrix [Edited version of GeeksForGeeks' Matrix Sort - https://www.geeksforgeeks.org/sort-given-matrix/]
        static void SortMatrix(int[,] mat, int rowSize, int colSize)
        {
            int[] arr = new int[rowSize * colSize]; // Create a temporary array with a length of @param rowSize * colSize
            int count = 0; // Counter variable for the temporary array

            // Copy elements of the multi-dimensional arrays one by one into the temporary array
            for(int row = 0; row < rowSize; row++)
                for(int col = 0; col < colSize; col++)
                    arr[count++] = mat[row, col];

            QuickSort(arr, 0, arr.Length - 1); // Calls the QuickSort function to sort the temporary array

            count = 0; // Resets the counter variable back to zero
            // Copy elements of the temporary array one by one into the multi-dimensional array
            for(int row = 0; row < rowSize; row++)
                for(int col = 0; col < colSize; col++)
                    mat[row, col] = arr[count++];
        }

        // Creates a randomized matrix with values [0, 10000] inclusive
        static int[,] RandomMatrix(int rowSize, int colSize)
        {
            int[,] arr = new int[rowSize, colSize]; 
            Random rng = new Random(); 

            for(int row = 0; row < rowSize; row++)
                for(int col = 0; col < colSize; col++)
                    arr[row, col] = rng.Next(0, 10001);

            return arr;
        }

        // Print all elements of a multi-dimensional array
        static void PrintMatrix(int[,] mat)
        {
            for(int row = 0; row < mat.GetLength(0); row++) 
            {
                for(int col = 0; col < mat.GetLength(1); col++)
                {
                    Console.Write(mat[row, col] + "\t");
                }
                Console.WriteLine();
            }
        }
    }
}
