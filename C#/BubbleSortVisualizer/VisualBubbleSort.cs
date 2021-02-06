using System;
using System.Collections.Generic;

namespace BubbleSortVisualizer
{
    class VisualBubbleSort
    {
        static void Main(string[] args)
        {
            Console.Title = "Bubble Sort Visualizer";
            Console.ForegroundColor = ConsoleColor.DarkCyan;
            
            Console.Write("Enter a length for the list: ");
            List<int> list = GenerateRandomList(Convert.ToInt32(Console.ReadLine()));
            BubbleSort(list);
            Console.ReadKey();
        }

        /* This method sorts the list using Bubble Sort, a single sorting algorithm that
        compares each adjacent elements and swaps them if they are in the wrong order. */
        static void BubbleSort(List<int> list)
        {
            int swapCount = 1; // Counting swap variable

            // Prints out the unsorted list [before it is being sorted].
            Console.Write("\nUnsorted: ");
            list.ForEach(elem => Console.Write(elem + " "));
            Console.WriteLine("\n");

            for(int x = 0; x < list.Count - 1; x++) {
                for(int y = 0; y < list.Count - x - 1; y++) {
                    if(list[y] > list[y + 1]) {
                        // Prints out list during swapping process
                        Console.Write("Swap #" + swapCount + ": ");
                        list.ForEach(elem => Console.Write(elem + " "));
                        Console.Write("- swapping: [" + list[y] + ", " + list[y + 1] + "]");
                        Console.Write(" -> swapped: [" + list[y + 1] + ", " + list[y] + "]\n");
                        Console.WriteLine();
                        swapCount++;

                        int temp = list[y];
                        list[y] = list[y + 1];
                        list[y + 1] = temp; 
                    }
                }
            }

            // Prints out the sorted list
            Console.Write("Sorted: ");
            list.ForEach(elem => Console.Write(elem + " "));
        }
        
        /* Generates a random integer list of length @param length with a value between
        [0, 100] inclusive. */
        static List<int> GenerateRandomList(int length) 
        {
            List<int> randomList = new List<int>(length);
            Random rng = new Random();
            for(int i = 0; i < length; i++)
                randomList.Add(rng.Next(0, 101));
            return randomList;
        }
    }
}