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
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
            };

            Console.WriteLine("Original Matrix:\n");
            PrintMatrix(mat);

            Console.WriteLine("\nExpanded Matrix:\n");
            int[,] exp = ExpandMatrix(mat, 2);
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
                    Console.Write(mat[i, j] + "\t");
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

            int rowBlock = rowSize / mat.GetLength(0);
            int colBlock = colSize / mat.GetLength(1);

            int[,] expMat = new int[rowSize, colSize];

            for(int row = 0; row < mat.GetLength(0); row++)
            {
                int rowOffset = row * rowBlock;
                int rowLen = rowOffset + rowBlock;

                for(int col = 0; col < mat.GetLength(1); col++)
                {
                    int colOffset = col * colBlock;
                    int colLen = colOffset + colBlock;

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

        // Expands the original matrix by a factor.
        //
        // Precondition: size of original matrix is greater than 0.
        // Precondition: factor is greater than 0.
        //
        // Postcondition: return a new and larger matrix with a size of matrix size * factor.
        // Postcondition: all values in the original matrix will be filled in square format.
        static int[,] ExpandMatrix(int[,] mat, int factor)
        {
            int rowSize = mat.GetLength(0) * factor;
            int colSize = mat.GetLength(1) * factor;

            int rowBlock = rowSize / mat.GetLength(0);
            int colBlock = colSize / mat.GetLength(1);

            int[,] expMat = new int[rowSize, colSize];

            for(int row = 0; row < mat.GetLength(0); row++)
            {
                int rowOffset = row * rowBlock;
                int rowLen = rowOffset + rowBlock;

                for(int col = 0; col < mat.GetLength(1); col++)
                {
                    int colOffset = col * colBlock;
                    int colLen = colOffset + colBlock;

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
