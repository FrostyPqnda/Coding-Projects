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
            List<int> list = new List<int>();
            for(int i = 0; i < 10; i++)
                list.Add(random.Next(10, 1000));
            list.ForEach(elem => Console.Write(elem + " "));
            
            Console.WriteLine("\n\nIs a sorted list? " + CheckSortedList(list));
            Console.ReadKey();
        }
        // Checks if the integer array is in ascending or descending order
        static bool CheckSortedArray(int[] arr)
        {
            bool ascendingOrder = true;
            bool descendingOrder = true;
            // Loop to check if the array is in ascending order
            for(int i = 0; i < arr.Length - 1; i++)
                if(arr[i] > arr[i + 1])
                    ascendingOrder = false;
            // Loop to check if the array is in descending order
            for(int i = 0; i < arr.Length - 1; i++)
                if(arr[i] < arr[i + 1])
                    descendingOrder = false;
            return ascendingOrder || descendingOrder;
        }
        // Checks if the integer list is in ascending or descending order
        static bool CheckSortedList(List<int> list)
        {
            bool ascendingOrder = true;
            bool descendingOrder = true;
            // Loop to check if the list is in ascending order
            for(int i = 0; i < list.Count - 1; i++)
                if(list[i] > list[i + 1])
                    ascendingOrder = false;
            // Loop to check if the list is in descending order
            for(int i = 0; i < list.Count - 1; i++)
                if(list[i] < list[i + 1])
                    descendingOrder = false;
            return ascendingOrder || descendingOrder;
        }
        // Checks if the double array is in ascending or descending order
        static bool CheckSortedArray(double[] arr)
        {
            bool ascendingOrder = true;
            bool descendingOrder = true;
            // Loop to check if the array is in ascending order
            for(int i = 0; i < arr.Length - 1; i++)
                if(arr[i] > arr[i + 1])
                    ascendingOrder = false;
            // Loop to check if the array is in descending order
            for(int i = 0; i < arr.Length - 1; i++)
                if(arr[i] < arr[i + 1])
                    descendingOrder = false;
            return ascendingOrder || descendingOrder;
        }
        // Checks if the double list is in ascending or descending order
        static bool CheckSortedList(List<double> list)
        {
            bool ascendingOrder = true;
            bool descendingOrder = true;
            // Loop to check if the list is in ascending order
            for(int i = 0; i < list.Count - 1; i++)
                if(list[i] > list[i + 1])
                    ascendingOrder = false;
            // Loop to check if the list is in descending order
            for(int i = 0; i < list.Count - 1; i++)
                if(list[i] < list[i + 1])
                    descendingOrder = false;
            return ascendingOrder || descendingOrder;
        }
        // Checks if the character array is in ascending or descending order
        static bool CheckSortedArray(char[] arr)
        {
            bool ascendingOrder = true;
            bool descendingOrder = true;
            // Loop to check if the array is in ascending order
            for(int i = 0; i < arr.Length - 1; i++)
                if(arr[i] > arr[i + 1])
                    ascendingOrder = false;
            // Loop to check if the array is in descending order
            for(int i = 0; i < arr.Length - 1; i++)
                if(arr[i] < arr[i + 1])
                    descendingOrder = false;
            return ascendingOrder || descendingOrder;
        }
        // Checks if the character list is in ascending or descending order
        static bool CheckSortedList(List<char> list)
        {
            bool ascendingOrder = true;
            bool descendingOrder = true;
            // Loop to check if the list is in ascending order
            for(int i = 0; i < list.Count - 1; i++)
                if(list[i] > list[i + 1])
                    ascendingOrder = false;
            // Loop to check if the list is in descending order
            for(int i = 0; i < list.Count - 1; i++)
                if(list[i] < list[i + 1])
                    descendingOrder = false;
            return ascendingOrder || descendingOrder;
        } 
        // Checks if the string array is in ascending or descending order
        static bool CheckSortedArray(string[] arr)
        {
            bool ascendingOrder = true;
            bool descendingOrder = true;
            // Loop to check if the array is in ascending order
            for(int i = 0; i < arr.Length - 1; i++)
                if(arr[i].CompareTo(arr[i + 1]) > 0)
                    ascendingOrder = false; 
            // Loop to check if the array is in descending order
            for(int i = 0; i < arr.Length - 1; i++)
                if(arr[i].CompareTo(arr[i + 1]) < 0)
                    descendingOrder = false;
            return ascendingOrder || descendingOrder;
        }
        // Checks if the string list is in ascending or descending order
        static bool CheckSortedList(List<string> list)
        {
            bool ascendingOrder = true;
            bool descendingOrder = true;
            // Loop to check if the list is in ascending order
            for(int i = 0; i < list.Count - 1; i++)
                if(list[i].CompareTo(list[i + 1]) > 0)
                    ascendingOrder = false; 
            // Loop to check if the list is in descending order
            for(int i = 0; i < list.Count - 1; i++)
                if(list[i].CompareTo(list[i + 1]) < 0)
                    descendingOrder = false;
            return ascendingOrder || descendingOrder;
        }
    }
}
