using System;
using System.Linq;
using System.Collections.Generic;
using LinearSearchAlgorithm;

namespace LinearSearchAlgorithm
{
    class LinearSearch
    {
        /* Uses a linear search algorithm to loop through the Lists of @param arr for @param key*/
        public int ListSearch(List<int> arr, int key)
        {
            for(int i = 0; i < arr.Count; i++)
            if(arr.ElementAt(i) == key)
                return i;
            return -1; // Returns -1 if not found
        }
        /* Uses a linear search algorithm to loop through the Arrays of @param arr for @param key*/
        public int ArraySearch(int[] arr, int key)
        {
            for(int i = 0; i < arr.Length; i++)
            if(arr[i] == key)
                return i;
            return -1; // Returns -1 if not found
        } 
        /* Uses a linear search algorithm to loop through the List of @param arr for @param key*/
        public int ListSearch(List<double> arr, double key)
        {
            for(int i = 0; i < arr.Count; i++)
            if(arr.ElementAt(i) == key)
                return i;
            return -1; // Returns -1 if not found
        }
        /* Uses a linear search algorithm to loop through the Arrays of @param arr for @param key*/
        public int ArraySearch(double[] arr, double key)
        {
            for(int i = 0; i < arr.Length; i++)
            if(arr[i] == key)
                return i;
            return -1; // Returns -1 if not found
        }

        
    }
}