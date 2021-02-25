public class Statistics
{
    // Returns the median of the array
    static double findMedian(int[] arr)
    {
        insertionSort(arr);
        if(arr.length % 2 != 0)
        {
            return arr[arr.length / 2];
        }
        else if(arr.length % 2 == 0)
        {
            double evenMedian = arr[arr.length / 2] + arr[(arr.length / 2) - 1];
            return evenMedian / 2;
        }
        else
        {
            return 0;
        }
    }

    // Returns the average of the array
    static double findMean(int[] arr)
    {
        double average = 0;
        for(int i = 0; i < arr.length; i++)
        {
            average += arr[i];
        }
        return (double)Math.round((average / arr.length) * 100) / 100;
    }

    // Returns the mode of the array [the most frequent number].
    // If two values occur equally, then it will return the first value
    static int findMode(int[] arr)
    {
        int mode = 0, count = 0;
        for(int i = 0; i < arr.length; i++)
        {
            if(countOccurrence(arr, arr[i]) > 1 && countOccurrence(arr, arr[i]) > count)
            {
                mode = arr[i];
                count++;
            }
        }
        return mode;
    }

    // Returns the range of the array [high - low]
    static int findRange(int[] arr)
    {
        int min = arr[0], max = arr[0];
        for(int i = 0; i < arr.length; i++)
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

    // Returns the standard deviation of the array
    static double findStandardDeviation(int[] arr)
    {
        double mean = findMean(arr), standardDeviation = 0;
        for(int item : arr)
        {
            standardDeviation += Math.pow(item - mean, 2);
        }
        return (double)Math.round(Math.sqrt(standardDeviation / arr.length) * 100) / 100;
    }

    // Sorts the array using the Insertion Sort algorithm
    static void insertionSort(int[] arr)
    {
        for(int i = 1; i < arr.length; i++)
        {
            int key = arr[i];
            int x = i - 1;
            // Move elements of arr[0 -> i - 1], that are
            // greater than key, to one position ahead of
            // their current position
            while(x >= 0 && arr[x] > key)
            {
                arr[x + 1] = arr[x];
                x--;
            }
            arr[x + 1] = key;
        }
    }
    
    // Count the number of time the element appears in the array
    static int countOccurrence(int[] arr, int elem)
    {
        int count = 0;
        for(int i = 0; i < arr.length; i++)
        {
            if(arr[i] == elem)
            {
                count++;
            }
        }
        return count;
    }
}