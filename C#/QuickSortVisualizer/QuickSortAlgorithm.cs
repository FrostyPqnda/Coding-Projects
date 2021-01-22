using System;
using System.Collections.Generic;

namespace QuickSortVisualizer
{
    class QuickSortAlgorithm
    {
        static void Main(string[] args)
        {
            List<int> list = GenerateRandomList(10);

            Console.Write("Unsorted: ");
            list.ForEach(elem => Console.Write(elem + " "));

            QuickSort(list, 0, list.Count - 1);
            Console.Write("\nSorted: ");
            list.ForEach(elem => Console.Write(elem + " "));
            Console.ReadKey();
        }

        public static void QuickSort(List<int> list, int low, int high)
        {
            if(low < high) {
                int pi = Partition(list, low, high);
                
                QuickSort(list, low, pi - 1);
                QuickSort(list, pi + 1, high);
            }
        }
        
        /*  */
        public static int Partition(List<int> list, int low, int high)
        {
            int pivot = list[high];
            int i = (low - 1);

            for(int x = low; x < high; x++) {
                if(list[x] < pivot) {
                    i++;
                    // Swap list[i] and list[x]
                    int temp = list[i];
                    list[i] = list[x];
                    list[x] = temp;
                }
            }

            // Swap list[i + 1] and list[high]
            int swap = list[i + 1];
            list[i + 1] = list[high];
            list[high] = swap;

            return (i + 1);
        }

        /* Generates a random integer list of length @param length with a value between
        [0, 100] inclusive. */
        public static List<int> GenerateRandomList(int length) 
        {
            List<int> randomList = new List<int>(length);
            Random rng = new Random();
            for(int i = 0; i < length; i++)
                randomList.Add(rng.Next(0, 101));
            return randomList;
        }
    }
}
