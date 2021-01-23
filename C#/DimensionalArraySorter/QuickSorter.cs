using System;
namespace DimensionalArraySorter 
{
    public class QuickSorter
    {
        // Sorts the arrays using the Merge Sort algorithm
        public void QuickSort(int[] arr, int low, int high)
        {
            if(low < high) {
                int pi = Partition(arr, low, high);
                
                QuickSort(arr, low, pi - 1);
                QuickSort(arr, pi + 1, high);
            }
        }
        
        /*  */
        int Partition(int[] arr, int low, int high)
        {
            int pivot = arr[high];
            int i = (low - 1);

            for(int x = low; x < high; x++) {
                if(arr[x] < pivot) {
                    i++;
                    // Swap arr[i] and arr[x]
                    int temp = arr[i];
                    arr[i] = arr[x];
                    arr[x] = temp;
                }
            }

            // Swap arr[i + 1] and arr[high]
            int swap = arr[i + 1];
            arr[i + 1] = arr[high];
            arr[high] = swap;

            return (i + 1);
        }
    }
}