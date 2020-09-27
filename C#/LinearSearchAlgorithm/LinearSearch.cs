using System;
using System.Linq;
using System.Collections.Generic;
using LinearSearchAlgorithm;

namespace LinearSearchAlgorithm
{
    class LinearSearch
    {
        public int ListSearch(List<int> arr, int key)
        {
            for(int i = 0; i < arr.Count; i++)
            if(arr.ElementAt(i) == key)
                return i;
            return -1;
        }

        public int ArraySearch(int[] arr, int key)
        {
            for(int i = 0; i < arr.Length; i++)
            if(arr[i] == key)
                return i;
            return -1;
        } 
    }
}