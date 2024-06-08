using System;
using System.Linq;

namespace JaggedSorter
{
    class JaggedArraySorter
    {
        static void Main(string[] args)
        {
            Console.Title = "Jagged Array Sorter";
            Console.ForegroundColor = ConsoleColor.DarkCyan;

            int[][] arr = createJaggedArray();

            // Displaying the values of 2D Jagged Array
            Console.WriteLine("Unsorted 2D Jagged Array\n");
            printJaggedArray(arr);

            Console.WriteLine("\n------------------------------");
            jaggedSort(arr);

            // Displaying the values of 2D Jagged Array
            Console.WriteLine("\nSorted 2D Jagged Array\n");
            printJaggedArray(arr);

            Console.ReadKey();
        }

        // Prints all elements of the 2D Jagged Array
        static void printJaggedArray(int[][] arr)
        {
            foreach(int[] row in arr)
            {
                foreach(int item in row)
                {
                    Console.Write(item + " ");
                }
                Console.WriteLine();
            }
        }

        // Sorts the rows of arrays in the 2D Jagged Array
        // using the Insertion Sort algorithm.
        //
        // Then, sort the 2D Jagged Array by length.
        static void jaggedSort(int[][] arr)
        {
            foreach(int[] row in arr)
                insertionSort(row);

            for(int i = 1; i < arr.Length; i++) {
                int[] key = arr[i];
                int x = i - 1;
                while(x >= 0 && arr[x].Length > key.Length) {
                    arr[x + 1] = arr[x];
                    x--;
                }
                arr[x + 1] = key;
            }
        }
        
        // Sorts the array using the Insertion Sort algorithm
        static void insertionSort(int[] arr)
        {
            for(int i = 1; i < arr.Length; i++)
            {
                int key = arr[i];
                int x = i - 1;
                // Move elements of arr[0 -> i - 1], that are
                // greater than key, to one position ahead of
                // their current position
                while(x >= 0 && arr[x] > key)
                {
                    arr[x + 1] = arr[x];
                    x--;
                }
                arr[x + 1] = key;
            }
        }

        // Function taken from https://stackoverflow.com/questions/29637606/how-to-make-a-random-2d-jagged-array-of-varying-length
        // Creates a randomized jagged array with a variable row and col between 5 - 10
        static int[][] createJaggedArray()
        {
            Random rand = new Random();
            int numRows = rand.Next(5, 11);
            int[][] arr = new int[numRows][];
            for(int i = 0; i < arr.Length; i++)
            {
                int numCols = rand.Next(5, 11);
                arr[i] = new int[numCols];
                // Populates each element in the array with a value between [10, 100]
                for(int x = 0; x < arr[i].Length; x++)
                {
                    arr[i][x] = rand.Next(10, 101); 
                }
            }
            return arr;
        }
    }
}
