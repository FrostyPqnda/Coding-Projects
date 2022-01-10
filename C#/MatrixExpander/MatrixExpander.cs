using System;

namespace MatrixExpander
{
    class MatrixExpander
    {
        static void Main(string[] args)
        {
            Console.ForegroundColor = ConsoleColor.DarkCyan;
            Console.Title = "Matrix Expander";

            int[,] mat = {
                {1, 2},
                {3, 4}
            };

            Console.WriteLine("Original Matrix:\n");
            PrintMatrix(mat);

            Console.WriteLine("\nExpanded Matrix:\n");
            int[,] exp = ExpandMatrix(mat, 2, 2);
            PrintMatrix(exp);

            Console.ReadKey();
        }
        
        // Prints the multidimensional array in block format.
        static void PrintMatrix(int[,] mat)
        {
            for(int i = 0; i < mat.GetLength(0); i++)
            {
                for(int j = 0; j < mat.GetLength(1); j++)
                {
                    Console.Write(mat[i, j] + " ");
                }
                Console.WriteLine();
            }
        }

        // Expands the original matrix by a row factor and col factor.
        //
        // Precondition: size of original matrix is greater than 0.
        // Precondition: row factor and col factor is greater than 0.
        //
        // Postcondition: return a matrix with a row size of the original matrix row size * row factor
        // and a column size of the original matrix col size * col factor.
        // Postcondition: Values of the original matrix are copied over into the new matrix in rectangular format.
        static int[,] ExpandMatrix(int[,] mat, int rowFactor, int colFactor)
        {
            int rowSize = mat.GetLength(0) * rowFactor;
            int colSize = mat.GetLength(1) * colFactor;

            int[,] expMat = new int[rowSize, colSize];

            for(int row = 0; row < mat.GetLength(0); row++)
            {
                int rowOffset = row * rowFactor;
                int rowLen = rowOffset + rowFactor;

                for(int col = 0; col < mat.GetLength(1); col++)
                {
                    int colOffset = col * colFactor;
                    int colLen = colOffset + colFactor;

                    for(int r = rowOffset; r < rowLen; r++)
                    {
                        for(int c = colOffset; c < colLen; c++)
                        {
                            expMat[r, c] = mat[row, col];
                        }
                    }
                }
            }
            
            return expMat;
        }
    }
}
