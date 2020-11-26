using System;

namespace DimensionalArraySorter 
{
    class MergeSorter
    {
        // Sorts the arrays using the Merge Sort algorithm
        public void MergeSort(int[] curr, int length)
        {
            // Base case - Array has length of 1
            if(length < 2)
                return;

            int midPoint = length / 2; // Find middle
            int[] leftArr = new int[midPoint]; // Left side of array up to the midpoint
            int[] rightArr = new int[length - midPoint]; //Right side of array starting from the midpoint 

            // Populate the first half into left
            for(int i = 0; i < midPoint; i++)
                leftArr[i] = curr[i];
            
            // Populate the second half into right
            for(int i = midPoint; i < length; i++)
                rightArr[i - midPoint] = curr[i];
            
            MergeSort(leftArr, midPoint); // Recursive call to sort the left array
            MergeSort(rightArr, length - midPoint); // Recursive call to sort the right array
            Merge(curr, leftArr, rightArr); // Merge the left and right arrays
        }

        // Combines the splitted arrays for the MergeSort method to use
        static void Merge(int[] curr, int[] left, int[] right)
        {
            int x = 0, y = 0, z = 0; // counter variables for the arrays [left, right, and curr]

            /* Loop through the left and right arrays and look for the lowest index value
            in the array (the lowest value overall). */
            while(x < left.Length && y < right.Length)
                /* Compares the left value and right value, takes the lowest one, and adds it to the
                current array. Then, it will increment the index for the array the value came from */
                if(left[x] <= right[y])
                    curr[z++] = left[x++];
                else    
                    curr[z++] = right[y++];

            // Places the remaining values into the current array.
            while(x < left.Length)
                curr[z++] = left[x++];
            
            while(y < right.Length)
                curr[z++] = right[y++];
        }
    }
}