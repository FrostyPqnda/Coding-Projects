using System;

namespace Data
{
    public class DataSet
    {
        private int[] arr;
        
        public DataSet(int[] arr)
        {
            this.arr = arr;
        }

        public DataSet(int size)
        {
            arr = GenerateSet(size);
        }

        // Finds the median (middle value) of the data set
        public double FindMedian()
        {
            InsertionSort(arr);
            if(arr.Length % 2 != 0)
            {
                return arr[arr.Length / 2];
            }
            else if(arr.Length % 2 == 0)
            {
                double evenMedian = arr[arr.Length / 2] + arr[(arr.Length / 2) - 1];
                return evenMedian / 2;
            }
            else
            {
                return 0;
            }

        }

        // Finds the mean (average) of the data set
        public double FindMean()
        {
            double average = 0;
            for(int i = 0; i < arr.Length; i++)
            {
                average += arr[i];
            }
            return Math.Round((average / arr.Length), 2);
        }

        // Finds the mode (most occuring number) of the data set
        public int FindMode()
        {
            int mode = 0, count = 0;
            for(int i = 0; i < arr.Length; i++)
            {
                if(CountOccurence(arr, arr[i]) > 1 && CountOccurence(arr, arr[i]) > count)
                {
                    mode = arr[i];
                    count++;
                }
            }
            return mode;
        }

        // Finds the range of the data set (max - min)
        public int FindRange()
        {
            int min = arr[0], max = arr[0];
            for(int i = 0; i < arr.Length; i++)
            {
                if(arr[i] < min)
                {
                    min = arr[i];
                }
                if(arr[i] > max)
                {
                    max = arr[i];
                }
            }
            return max - min;
        }

        // Finds the standard deviation (spreadness) of the data set 
        public double FindStandardDeviation()
        {
            double mean = FindMean(), standardDeviation = 0;
            foreach(int item in arr)
            {
                standardDeviation += Math.Pow(item - mean, 2);
            }
            return Math.Round(Math.Sqrt(standardDeviation / arr.Length), 2);
        }

        private int[] GenerateSet(int size)
        {
            int[] arr = new int[size];
            Random rand = new Random();
            for(int i = 0; i < size; i++)
            {
                arr[i] = rand.Next(0, 101); 
            }
            return arr;
        }

        // Counts which number occurs most in the data set
        private int CountOccurence(int[] arr, int elem)
        {
            int count = 0;
            for(int i = 0; i < arr.Length; i++)
            {
                if(arr[i] == elem)
                {
                    count++;
                }
            }
            return count;
        }
        
        // Sorts the array via insertion sort
        private void InsertionSort(int[] arr)
        {
            for(int i = 1; i < arr.Length; i++)
            {
                int key = arr[i];
                int x = i - 1;
                while(x >= 0 && arr[x] > key)
                {
                    arr[x + 1] = arr[x];
                    x--;
                }
                arr[x + 1] = key;
            }
        }

        public override string ToString()
        {
            string str = "[" + string.Join(", ", arr) + "]\n";
            string mean = "Mean: " + FindMean() + "";
            string median = "Median: " + FindMedian() + "";
            string mode = "Mode: " + FindMode() + "";
            string range = "Range: " + FindRange() + "";
            string std = "Standard Deviation: " + FindStandardDeviation() + "";

            return str + "\n" + median + "\n" + mean + "\n" + mode + "\n" + range + "\n" + std;
        }
    }
}
