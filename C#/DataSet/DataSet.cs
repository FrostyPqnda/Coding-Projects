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

        public double findMedian()
        {
            insertionSort(arr);
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

        public double findMean()
        {
            double average = 0;
            for(int i = 0; i < arr.Length; i++)
            {
                average += arr[i];
            }
            return Math.Round((average / arr.Length), 2);
        }

        public int findMode()
        {
            int mode = 0, count = 0;
            for(int i = 0; i < arr.Length; i++)
            {
                if(countOccurence(arr, arr[i]) > 1 && countOccurence(arr, arr[i]) > count)
                {
                    mode = arr[i];
                    count++;
                }
            }
            return mode;
        }

        public int findRange()
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

        public double findStandardDeviation()
        {
            double mean = findMean(), standardDeviation = 0;
            foreach(int item in arr)
            {
                standardDeviation += Math.Pow(item - mean, 2);
            }
            return Math.Round(Math.Sqrt(standardDeviation / arr.Length), 2);
        }

        private int countOccurence(int[] arr, int elem)
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

        private void insertionSort(int[] arr)
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
            string mean = "Mean: " + findMean() + "";
            string median = "Median: " + findMedian() + "";
            string mode = "Mode: " + findMode() + "";
            string range = "Range: " + findRange() + "";
            string std = "Standard Deviation: " + findStandardDeviation() + "";

            return str + "\n" + median + "\n" + mean + "\n" + mode + "\n" + range + "\n" + std;
        }
    }
}
