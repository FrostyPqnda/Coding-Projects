import java.util.ArrayList;

/**
 * Binary Search is a search algorithm that searches through a sorted array by
 * repeatedly dividing the search interval in half. If the target is less than
 * the item in the middle of the array, it will search for the target in the
 * lower half. Otherwise, it will search for it in the upper half.
 */
public class BinarySearch extends MergeSortAlgorithm 
{
    static SortedArrayChecker sac = new SortedArrayChecker();

    public static int arrayBinarySearch(int[] arr, int low, int high, int target) 
    {
        if(high >= low)
        {
            int mid = low + (high - low) / 2;
            // Element is present at the middle itself
            if(arr[mid] == target)
                return mid;
            // Element is smaller than mid
            if(arr[mid] > target)
                return arrayBinarySearch(arr, low, mid - 1, target);
            // Element is greater than mid
            return arrayBinarySearch(arr, mid + 1, high, target);
        }
        return -1;
    }

    public static int arrayListBinarySearch(ArrayList<Integer> arrList, int low, int high, int target) 
    {
        if(high >= low)
        {
            int mid = low + (high - low) / 2;
            // Element is present at the middle itself
            if(arrList.get(mid) == target)
                return mid;
            // Element is smaller than mid
            if(arrList.get(mid) > target)
                return arrayListBinarySearch(arrList, low, mid - 1, target);
            // Element is greater than mid
            return arrayListBinarySearch(arrList, mid + 1, high, target);
        }
        return -1;
    }

    public static int arrayBinarySearch(double[] arr, int low, int high, double target) 
    {
        if(high >= low)
        {
            int mid = low + (high - low) / 2;
            // Element is present at the middle itself
            if(arr[mid] == target)
                return mid;
            // Element is smaller than mid
            if(arr[mid] > target)
                return arrayBinarySearch(arr, low, mid - 1, target);
            // Element is greater than mid
            return arrayBinarySearch(arr, mid + 1, high, target);
        }
        return -1; // Target not found
    }

    public static int arrayListBinarySearch(ArrayList<Double> arrList, int low, int high, double target)
    {
        if(high >= low)
        {
            int mid = low + (high - low) / 2;
            // Element is present at the middle itself
            if(arrList.get(mid) == target)
                return mid;
            // Element is smaller than mid
            if(arrList.get(mid) > target)
                return arrayListBinarySearch(arrList, low, mid - 1, target);
            // Element is greater than mid
            return arrayListBinarySearch(arrList, mid + 1, high, target);
        }
        return -1;
    }

    public static int arrayBinarySearch(char[] arr, int low, int high, char target)
    {
        if(high >= low)
        {
            int mid = low + (high - low) / 2;
            // Element is present at the middle itself
            if(arr[mid] == target)
                return mid;
            // Element is smaller than mid
            if(arr[mid] > target)
                return arrayBinarySearch(arr, low, mid - 1, target);
            // Element is greater than mid
            return arrayBinarySearch(arr, mid + 1, high, target);
        }
        return -1; // Target not found
    }

    public static int arrayListBinarySearch(ArrayList<Character> arrList, int low, int high, char target)
    {
        if(high >= low)
        {
            int mid = low + (high - low) / 2;
            // Element is present at the middle itself
            if(arrList.get(mid) == target)
                return mid;
            // Element is smaller than mid
            if(arrList.get(mid) > target)
                return arrayListBinarySearch(arrList, low, mid - 1, target);
            // Element is greater than mid
            return arrayListBinarySearch(arrList, mid + 1, high, target);
        }
        return -1;
    }

    public static int arrayBinarySearch(String[] arr, int low, int high, String target)
    {
        if(high >= low)
        {
            int mid = low + (high - low) / 2;
            // Element is present at the middle itself
            if(arr[mid].equals(target))
                return mid;
            // Element is smaller than mid
            if(arr[mid].compareTo(target) > 0)
                return arrayBinarySearch(arr, low, mid - 1, target);
            // Element is greater than mid
            return arrayBinarySearch(arr, mid + 1, high, target);
        }
        return -1; // Target not found
    }

    public static int arrayListBinarySearch(ArrayList<String> arrList, int low, int high, String target)
    {
        if(high >= low)
        {
            int mid = low + (high - low) / 2;
            // Element is present at the middle itself
            if(arrList.get(mid).equals(target))
                return mid;
            // Element is smaller than mid
            if(arrList.get(mid).compareTo(target) > 0)
                return arrayListBinarySearch(arrList, low, mid - 1, target);
            // Element is greater than mid
            return arrayListBinarySearch(arrList, mid + 1, high, target);
        }
        return -1;
    }
}