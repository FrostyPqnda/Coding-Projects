public class Sorter
{
    /**
     * Bubble Sort is simple sorting algorithm that
     * repeatedly swaps the adjacent elements if they
     * are in the wrong order.
     */
    public void bubbleSort(int[] arr)
    {
        for(int i = 0; i < arr.length - 1; i++)
        {
            for(int x = 0; x < arr.length - i - 1; x++)
            {
                if(arr[x] > arr[x + 1])
                {
                    // Swaps arr[x] and arr[x + 1]
                    swap(arr, x, x+1);
                }
            }
        }
    }

    /**
     * Selection Sort sorts the array by finding the smallest element 
     * (considering ascending order) from unsorted part and putting it 
     * at the beginning of the array.
     */
    public void selectionSort(int[] arr)
    {
        // One by one move boundary of the unsorted subarray
        for(int i = 0; i < arr.length - 1; i++) 
        {
            // Find the minimum index in the unsorted array
            int minIndex = i; 
            for(int x = i + 1; x < arr.length; x++) 
            {
                if(arr[x] < arr[minIndex])
                {
                    minIndex = x;
                }
            }
            // Swap the found minimum element with the first elements
            swap(arr, minIndex, i);
        }
    }
    
    /**
     * Insertion Sort is a simple sorting algorithm that virtually splits the
     * array into unsorted and sorted. Values from the unsorted part are picked
     * and inserted into the correct position in the sorted part.
     */
    public void insertionSort(int[] arr)
    {
        for(int i = 1; i < arr.length; ++i)
        {   
            int key = arr[i];
            int x = (i - 1);
            /**
             * Move elements arr[0...i-1], that are greater than the key
             * to one position ahead of its current position
             */
            while(x >= 0 && arr[x] > key)
            {
                arr[x + 1] = arr[x];
                x--;
            }
            arr[x + 1] = key;
        }
    }

    /**
     * Merge Sort sorts the array through the Divide-and-Conquer algorithm, in which it will 
     * split the array into two halves, sorts the two splitted array and the merge them back 
     * into one.
     */
    public void mergeSort(int[] arr, int length)
    {
        // Base case - array length is 1
        if(length < 2) 
        {
            return;
        }
        
        // Find the middle
        int mid = length / 2;

        // left and right arrays
        int[] left = new int[mid];
        int[] right = new int[length - mid];

        // Populate first half into the left array
        for(int i = 0; i < mid; i++) 
        {
            left[i] = arr[i];
        }
        // Populate the second half into the right array
        for(int i = mid; i < length; i++) 
        {
            right[i - mid] = arr[i];
        }
        
        // Recursive calls
        mergeSort(left, mid);
        mergeSort(right, length - mid);

        // Combines the two halves of the array back into the original
        merge(arr, left, right);
    }

    // Merges the left array and right array back into the current array
    void merge(int[] curr, int[] left, int[] right)
    {
        // Index incrementer variable
        int leftIndex = 0, rightIndex = 0, currIndex = 0; 
        // Loop through both the left and right arrays
        while(leftIndex < left.length && rightIndex < right.length) 
        {
            /**
             * Compares the left value and the right value and takes
             * the lowest one and adds it to the curr array. Then, it
             * increments up the index for the array where it just took
             * the value. 
             */
            if(left[leftIndex] <= right[rightIndex]) 
            {
                curr[currIndex++] = left[leftIndex++];
            } 
            else 
            {    
                curr[currIndex++] = right[rightIndex++];
            }
        }
        /**
         * If one of the arrays still have values remaining,
         * these calls will place the remaining values in 
         * the curr array.
         */
        while(leftIndex < left.length) 
        {
            curr[currIndex++] = left[leftIndex++];
        }
        while(rightIndex < right.length) 
        {
            curr[currIndex++] = right[rightIndex++];
        }
    }

    /**
     * QuickSort is a Divide-and-Conquer sorting algorithm that picks an element as
     * the pivot and partitions the given array around the pivot.
     */
    public void quickSort(int[] arr, int low, int high)
    {
        if(low < high)
        {
            // pi is the partioning index, arr[pi] is now at the right place
            int pi = partition(arr, low, high);
            // Recursively sort the array before partition and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    /**
     * Takes the last element as the pivot, places that elemet at its correct position
     * in the sorted array, and places all elements smaller to the left of the pivot
     * and all elements greater to the right of the pivot.
     */
    int partition(int[] arr, int low, int high)
    {
        int pivot = arr[high]; // The pivot 
        int i = (low - 1); // Index of smaller element
       
        for(int x = low; x < high; x++)
        {
            // If the current element is smaller than the pivot
            if(arr[x] < pivot) 
            {
                i++;
                // Swap arr[i] and arr[x];
                swap(arr, i, x); 
            } 
        }
        // Swap arr[i + 1] and arr[high] (or pivot)
        swap(arr, i + 1, high);
        return (i + 1);
    }

    // Swap method to switch two elements of an array
    void swap(int[] arr, int x, int y)
    {
        int swap = arr[x];
        arr[x] = arr[y];
        arr[y] = swap;
    }
}