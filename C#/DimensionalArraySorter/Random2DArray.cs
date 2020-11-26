using System;

namespace DimensionalArraySorter
{
    class Random2DArray
    {
        // Creates a randomized 2D array with a row length of @param rowLength and a column length of @param columnLength
        public int[,] GenerateRandom2DArray(int rowLength, int columnLength)
        {
            int[,] arr = new int[rowLength, columnLength]; // Create a 2D array with a size of @param rowLength * @param columnLength
            Random rng = new Random(); // Create object from the Random class

            // Populate the 2D array with random value from the range of [0, 10000] inclusive
            for(int row = 0; row < rowLength; row++)
                for(int col = 0; col < columnLength; col++)
                    arr[row, col] = rng.Next(0, 10001);

            return arr; // Return the randomized 2D array
        }
    }
}