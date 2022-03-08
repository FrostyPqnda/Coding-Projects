using System;

namespace Data
{
    public class DataSet
    {
        private int[] data;
        
        public DataSet(int[] data)
        {
            this.data = data;
        }

        public DataSet(int size)
        {
            data = GenerateSet(size);
            InsertionSort(data);
        }

        // Finds the median (middle value) of the data set
        public double FindMedian()
        {
            InsertionSort(data);
            double median = 0;
            if(data.Length % 2 != 0)
            {
                median = data[data.Length / 2];
            }
            else
            {
                double evenMedian = data[data.Length / 2] + data[(data.Length / 2) - 1];
                median = evenMedian / 2;
            }

            return median;
        }

        // Finds the mean (average) of the data set
        public double FindMean()
        {
            double average = 0;
            for(int i = 0; i < data.Length; i++)
            {
                average += data[i];
            }
            return Math.Round((average / data.Length), 2);
        }

        // Finds the mode (most occuring number) of the data set
        public int FindMode()
        {
            int mode = 0, count = 0;
            for(int i = 0; i < data.Length; i++)
            {
                if(CountOccurence(data, data[i]) > 1 && CountOccurence(data, data[i]) > count)
                {
                    mode = data[i];
                    count++;
                }
            }
            return mode;
        }

        // Finds the range of the data set (max - min)
        public int FindRange()
        {
            int min = data[0], max = data[0];
            for(int i = 0; i < data.Length; i++)
            {
                if(data[i] < min)
                {
                    min = data[i];
                }
                if(data[i] > max)
                {
                    max = data[i];
                }
            }
            return max - min;
        }

        // Finds the standard deviation (spreadness) of the data set 
        public double FindStandardDeviation()
        {
            double mean = FindMean(), standardDeviation = 0;
            foreach(int item in data)
            {
                standardDeviation += Math.Pow(item - mean, 2);
            }
            return Math.Round(Math.Sqrt(standardDeviation / data.Length), 2);
        }

        // Returns a generated set of numbers
        private int[] GenerateSet(int size)
        {
            int[] data = new int[size];
            Random rand = new Random();
            for(int i = 0; i < size; i++)
            {
                data[i] = rand.Next(0, 101); 
            }
            return data;
        }

        // Counts which number occurs most in the data set
        private int CountOccurence(int[] data, int elem)
        {
            int count = 0;
            for(int i = 0; i < data.Length; i++)
            {
                if(data[i] == elem)
                {
                    count++;
                }
            }
            return count;
        }
        
        // Sorts the data via insertion sort
        public void InsertionSort(int[] data)
        {
            for(int i = 1; i < data.Length; i++)
            {
                int key = data[i];
                int j = i - 1;
            
                while(j >= 0 && key < data[j])
                {
                    data[j + 1] = data[j];
                    j--;
                }
                data[j + 1] = key;
            }
        }

        public int[] GetData()
        {
            return data;
        }

        public override string ToString()
        {
            string str = "[" + string.Join(", ", data) + "]\n";
            string mean = "Mean: " + FindMean() + "";
            string median = "Median: " + FindMedian() + "";
            string mode = "Mode: " + FindMode() + "";
            string range = "Range: " + FindRange() + "";
            string std = "Standard Deviation: " + FindStandardDeviation() + "";

            return str + "\n" + median + "\n" + mean + "\n" + mode + "\n" + range + "\n" + std;
        }
    }
}
