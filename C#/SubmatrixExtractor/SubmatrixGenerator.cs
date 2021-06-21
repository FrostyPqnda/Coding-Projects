using System;

namespace SubmatrixExtractor
{
    class SubmatrixGenerator
    {
        static void Main(string[] args)
        {
            Console.Title = "Submatrix Extractor";
            Console.ForegroundColor = ConsoleColor.DarkCyan;

            int[,] mat = RandomMatrix(8, 8);
            Console.WriteLine("Original: \n");
            PrintMatrix(mat);

            Console.WriteLine("\nExtracted Submatrix: \n");
            int[,] subMat = ExtractSubmatrix(mat, 5, 8, 4, 7);
            PrintMatrix(subMat);

            Console.ReadKey();
        }
        
        // Extracts a submatrix from the original matrix
        // Condition: 0 ≤ startRow < endRow < mat.length
        // Condition: 0 ≤ startCol < endCol < mat[0].length
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

        // Creates a randomized matrix with values [100, 999] inclusive.
        static int[,] RandomMatrix(int rowSize, int colSize)
        {
            int[,] arr = new int[rowSize, colSize]; 
            Random rng = new Random();

            for(int row = 0; row < rowSize; row++)
                for(int col = 0; col < colSize; col++)
                    arr[row, col] = rng.Next(100, 1000);

            return arr;
        }

        // Print all elements of a matrix
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
