using System;
using System.Collections.Generic;

namespace CheckSortedData
{
    class SortedDataChecker
    {
        static void Main(string[] args)
        {
            string[] arr = {"Lion", "Bacon", "Frog", "Toad"};
            Console.WriteLine(CheckArray(arr));
            Console.ReadKey();
        }
        // Checks if the integer array is in ascending or descending order
        static bool CheckArray(int[] arr)
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
        static bool CheckList(List<int> list)
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
        static bool CheckArray(double[] arr)
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
        static bool CheckList(List<double> list)
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
        static bool CheckArray(char[] arr)
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
        static bool CheckList(List<char> list)
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
        static bool CheckArray(string[] arr)
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
        static bool CheckList(List<string> list)
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
