/**
 * Merge Sort sorts the array through the Divide-and-Conquer algorithm, in which
 * it will split the array into two halves, sorts the two splitted array and then
 * merge them back into one.
 */
public class MergeSortAlgorithm
{
    public static void mergeSort(int[] arr, int length) 
    {
        // Base case - array length is 1
        if (length < 2)
            return;
        // Find the middle
        int mid = length / 2;
        // left and right arrays
        int[] left = new int[mid];
        int[] right = new int[length - mid];
        // Populate first half into the left array
        for (int i = 0; i < mid; i++)
            left[i] = arr[i];
        // Populate the second half into the right array
        for (int i = mid; i < length; i++)
            right[i - mid] = arr[i];
        // Recursive calls
        mergeSort(left, mid);
        mergeSort(right, length - mid);
        // Combines the two halves of the array back into the original
        merge(arr, left, right);
    }

    public static void mergeSort(double[] arr, int length) 
    {
        // Base case - array length is 1
        if (length < 2)
            return;
        // Find the middle
        int mid = length / 2;
        // left and right arrays
        double[] left = new double[mid];
        double[] right = new double[length - mid];
        // Populate first half into the left array
        for (int i = 0; i < mid; i++)
            left[i] = arr[i];
        // Populate the second half into the right array
        for (int i = mid; i < length; i++)
            right[i - mid] = arr[i];
        // Recursive calls
        mergeSort(left, mid);
        mergeSort(right, length - mid);
        // Combines the two halves of the array back into the original
        merge(arr, left, right);
    }
    
    public static void mergeSort(char[] arr, int length) 
    {
        // Base case - array length is 1
        if (length < 2)
            return;
        // Find the middle
        int mid = length / 2;
        // left and right arrays
        char[] left = new char[mid];
        char[] right = new char[length - mid];
        // Populate first half into the left array
        for (int i = 0; i < mid; i++)
            left[i] = arr[i];
        // Populate the second half into the right array
        for (int i = mid; i < length; i++)
            right[i - mid] = arr[i];
        // Recursive calls
        mergeSort(left, mid);
        mergeSort(right, length - mid);
        // Combines the two halves of the array back into the original
        merge(arr, left, right);
    }

    public static void mergeSort(String[] arr, int length) 
    {
        // Base case - array length is 1
        if (length < 2)
            return;
        // Find the middle
        int mid = length / 2;
        // left and right arrays
        String[] left = new String[mid];
        String[] right = new String[length - mid];
        // Populate first half into the left array
        for (int i = 0; i < mid; i++)
            left[i] = arr[i];
        // Populate the second half into the right array
        for (int i = mid; i < length; i++)
            right[i - mid] = arr[i];
        // Recursive calls
        mergeSort(left, mid);
        mergeSort(right, length - mid);
        // Combines the two halves of the array back into the original
        merge(arr, left, right);
    }

    // Merges the left array and right array back into the current array
    static void merge(int[] curr, int[] left, int[] right) 
    {
        // Index incrementer variable
        int leftIndex = 0, rightIndex = 0, currIndex = 0;
        // Loop through both the left and right arrays
        while (leftIndex < left.length && rightIndex < right.length) 
        {
            /**
             * Compares the left value and the right value and takes the lowest one and adds
             * it to the curr array. Then, it increments up the index for the array where it
             * just took the value.
             */
            if (left[leftIndex] <= right[rightIndex])
                curr[currIndex++] = left[leftIndex++];
            else
                curr[currIndex++] = right[rightIndex++];
        }
        /**
         * If one of the arrays still have values remaining, these calls will place the
         * remaining values in the curr array.
         */
        while (leftIndex < left.length) 
        {
            curr[currIndex++] = left[leftIndex++];
        }
        while (rightIndex < right.length) 
        {
            curr[currIndex++] = right[rightIndex++];
        }
    }

    static void merge(double[] curr, double[] left, double[] right) 
    {
        // Index incrementer variable
        int leftIndex = 0, rightIndex = 0, currIndex = 0;
        // Loop through both the left and right arrays
        while (leftIndex < left.length && rightIndex < right.length) 
        {
            /**
             * Compares the left value and the right value and takes the lowest one and adds
             * it to the curr array. Then, it increments up the index for the array where it
             * just took the value.
             */
            if (left[leftIndex] <= right[rightIndex])
                curr[currIndex++] = left[leftIndex++];
            else
                curr[currIndex++] = right[rightIndex++];
        }
        /**
         * If one of the arrays still have values remaining, these calls will place the
         * remaining values in the curr array.
         */
        while (leftIndex < left.length) 
        {
            curr[currIndex++] = left[leftIndex++];
        }
        while (rightIndex < right.length) 
        {
            curr[currIndex++] = right[rightIndex++];
        }
    }

    static void merge(char[] curr, char[] left, char[] right) 
    {
        // Index incrementer variable
        int leftIndex = 0, rightIndex = 0, currIndex = 0;
        // Loop through both the left and right arrays
        while (leftIndex < left.length && rightIndex < right.length) 
        {
            /**
             * Compares the left value and the right value and takes the lowest one and adds
             * it to the curr array. Then, it increments up the index for the array where it
             * just took the value.
             */
            if (left[leftIndex] <= right[rightIndex])
                curr[currIndex++] = left[leftIndex++];
            else
                curr[currIndex++] = right[rightIndex++];
        }
        /**
         * If one of the arrays still have values remaining, these calls will place the
         * remaining values in the curr array.
         */
        while (leftIndex < left.length) 
        {
            curr[currIndex++] = left[leftIndex++];
        }
        while (rightIndex < right.length) 
        {
            curr[currIndex++] = right[rightIndex++];
        }
    }

    static void merge(String[] curr, String[] left, String[] right) 
    {
        // Index incrementer variable
        int leftIndex = 0, rightIndex = 0, currIndex = 0;
        // Loop through both the left and right arrays
        while (leftIndex < left.length && rightIndex < right.length) 
        {
            /**
             * Compares the left value and the right value and takes the lowest one and adds
             * it to the curr array. Then, it increments up the index for the array where it
             * just took the value.
             */
            if (left[leftIndex].compareTo(right[rightIndex]) <= 0)
                curr[currIndex++] = left[leftIndex++];
            else
                curr[currIndex++] = right[rightIndex++];
        }
        /**
         * If one of the arrays still have values remaining, these calls will place the
         * remaining values in the curr array.
         */
        while (leftIndex < left.length) 
        {
            curr[currIndex++] = left[leftIndex++];
        }
        while (rightIndex < right.length) 
        {
            curr[currIndex++] = right[rightIndex++];
        }
    }
}