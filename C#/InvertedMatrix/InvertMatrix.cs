using System;

namespace InvertedMatrix
{
    class InvertMatrix
    {
        static void Main(string[] args)
        {
            Console.Title = "Inverted Matrix";
            Console.ForegroundColor = ConsoleColor.DarkCyan;
            
            int[,] arr ={
                {1, 1, 1},
                {2, 2, 2},
                {3, 3, 3}
            };
            
            Console.WriteLine("Original Matrix:\n");
            PrintMatrix(arr);

            Console.WriteLine("\nInverted Matrix:\n");
            int[,] invArr = Invert(arr);
            PrintMatrix(invArr);

            Console.ReadKey();
        }

        // Creates an inverted matrix that swaps the original
        // matrix's row and column with each other.
        static int[,] Invert(int[,] arr)
        {
            int[,] invArr = new int[arr.GetLength(1), arr.GetLength(0)];
            int r = 0, c = 0;

            for(int col = 0; col < arr.GetLength(1); col++)
            {
                for(int row = 0; row < arr.GetLength(0); row++)
                {
                    invArr[r, c] = arr[row, col]; 
                    c++;
                    if(c == invArr.GetLength(1))
                    {
                        c = 0;
                        r++;
                    }
                }
            }

            return invArr;
        }

        // Print all elements of a multi-dimensional array
        static void PrintMatrix(int[,] curr)
        {
            for(int row = 0; row < curr.GetLength(0); row++) 
            {
                for(int col = 0; col < curr.GetLength(1); col++)
                {
                    Console.Write(curr[row, col] + " ");
                }
                Console.WriteLine();
            }
        }
    }
}
