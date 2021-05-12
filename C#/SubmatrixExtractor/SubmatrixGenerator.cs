using System;

namespace SubmatrixExtractor
{
    class SubmatrixGenerator
    {
        static void Main(string[] args)
        {
            Console.Title = "Submatrix Extractor";
            Console.ForegroundColor = ConsoleColor.DarkCyan;

            int[,] mat = GenerateRandomMatrix(5, 4);
            Console.WriteLine("Original: \n");
            PrintMatrix(mat);

            Console.WriteLine("\nSubmatrix: \n");
            int[,] subMat = ExtractSubmatrix(mat, 1, 4, 1, 3);
            PrintMatrix(subMat);

            Console.ReadKey();
        }
        
        // Extracts a submatrix from the original matrix
        // Condition: 0 ≤ startRow < endRow, 0 ≤ startCol < endCol
        static int[,] ExtractSubmatrix(int[,] mat, int startRow, int endRow, int startCol, int endCol)
        {
            int rowSize = endRow - startRow, colSize = endCol - startCol;
            int[,] subMat = new int[rowSize, colSize];

            for(int row = startRow; row < endRow; row++)
            {
                for(int col = startCol; col < endCol; col++)
                {
                    int r = row - startRow, c = col - startCol;
                    subMat[r, c] = mat[row, col];
                }
            }

            return subMat;
        }

        // Creates a randomized 2D array with a row length of @param rowSize and a column length of @param colSize
        static int[,] GenerateRandomMatrix(int rowSize, int colSize)
        {
            int[,] arr = new int[rowSize, colSize]; // Create a matrix with a size of @param  * @param colSize
            Random rng = new Random(); // Create object from the Random class

            // Populate the 2D array with random value from the range of [100, 999] inclusive
            for(int row = 0; row < rowSize; row++)
                for(int col = 0; col < colSize; col++)
                    arr[row, col] = rng.Next(100, 1000);

            return arr; // Return the randomized 2D array
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
