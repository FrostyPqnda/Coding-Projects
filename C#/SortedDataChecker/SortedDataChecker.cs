using System;
using System.Collections.Generic;

namespace CheckSortedData
{
    class SortedDataChecker
    {
        static void Main(string[] args)
        {
            Console.Title = "Sorted Data Checker";
            Console.ForegroundColor = ConsoleColor.DarkCyan;

            Random random = new Random();
            int[] arr = new int[10];
    
            for(int i = 0; i < 10; i++)
            {
                arr[i] = random.Next(10, 1000);
            }
            
            foreach(int item in arr) {
                Console.Write(item + " ");
            }
            
            Console.WriteLine("\nIs a sorted array? " + IsSorted(arr) + "\n");

            Sort(arr);
            foreach(int item in arr) {
                Console.Write(item + " ");
            }

            Console.WriteLine("\nIs a sorted array? " + IsSorted(arr));
            Console.ReadKey();
        }
        
        // Checks if the integer array is in ascending or descending order
        static bool IsSorted(int[] arr)
        {
            bool ascendingOrder = true;
            bool descendingOrder = true;

            // Loop to check if the array is in ascending order
            for(int i = 0; i < arr.Length - 1; i++) 
            {
                if(arr[i] > arr[i + 1]) 
                {
                    ascendingOrder = false;
                }
            }

            // Loop to check if the array is in descending order
            for(int i = 0; i < arr.Length - 1; i++)
            {
                if(arr[i] < arr[i + 1])
                {
                    descendingOrder = false;
                }
            }

            return ascendingOrder || descendingOrder;
        }

        static void Sort(int[] arr) 
        {
            for(int i = 0; i < arr.Length; i++)
            {
                int minIndex = i;

                for(int j = i + 1; j < arr.Length; j++)
                {
                    if(arr[j] < arr[minIndex])
                    {
                        minIndex = j;
                    }
                }

                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }
}
