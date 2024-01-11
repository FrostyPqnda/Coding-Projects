import java.util.Arrays;

class BucketSort {
    public static void main(String[] args) {
        int[] arr = {5, 1, 4, 9, 1, 3, 7, 2, 7, 0, 5, 8, 9, 4, 2, 8, 1, 8, 4, 2};
        System.out.println(Arrays.toString(arr));
        System.out.println();
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    static void sort(int[] arr) {
        int maxElem = max(arr, arr.length - 1);
        int[] bucket = new int[maxElem + 1];
        
        // Store frequency in bucket
        for(int item : arr)
            bucket[item]++;

        // Get last occurence of each item
        for(int i = 1; i <= maxElem; i++)
            bucket[i] += bucket[i - 1];

        int[] output = new int[arr.length];

        // Sort the array
        for(int i = arr.length - 1; i >= 0; i--) {
            output[bucket[arr[i]] - 1] = arr[i];
            bucket[arr[i]]--;
        }

        // Copy back into original array
        for(int i = 0; i < arr.length; i++)
            arr[i] = output[i];
    }

    static int max(int[] arr, int n) {
        if(n == 0)
            return arr[0];
        return Math.max(arr[n], max(arr, n - 1));
    }
}