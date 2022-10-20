import java.util.*;

public class FilterZero 
{
    public static void main(String[] args) {
        int[] arr = {0, 1};
        System.out.println(Arrays.toString(arr));
        int[] filter = filterZero(arr);
        System.out.println(Arrays.toString(filter));
    }

    /**
     * @param arr
     * @return a filtered array with 0's removed
     * 
     * Filters out all zeroes in the array.
     */
    static int[] filterZero(int[] arr)
    {
        // If the given array contains only the 0 value
        // then return the empty array
        if(allZeroes(arr)) {
            return new int[0];
        }

        // Return the original array if the size is 1
        if(arr.length == 1) {
            return arr;
        }

        // Filters out the zeroes by shifting them to the
        // right. The updated value of [index] will be the
        // size of the filtered array
        int index = 0;
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] != 0) {
                arr[index++] = arr[i];
            }
        }

        // Copy all values from the array to the filtered array and return it
        int[] filtered = new int[index];
        System.arraycopy(arr, 0, filtered, 0, index);
        return filtered;
    }

    // Checks if the given array is the 0 array
    static boolean allZeroes(int[] arr) {
        boolean flag = true;

        for(int i = 0; i < arr.length; i++) {
        if(arr[i] != 0) {
            flag = false;
            break;
        }
        }

        return flag;
    }

}