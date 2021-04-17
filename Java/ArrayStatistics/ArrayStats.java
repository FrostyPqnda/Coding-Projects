import java.util.Arrays;
public class ArrayStats extends Statistics
{
    public static void main(String[] args)
    {
        int[] arr = {76, 45, 23, 89, 76, 76, 89, 54};
        System.out.println("Data Set: " + Arrays.toString(arr));
        System.out.println("Median: " + findMedian(arr));
        System.out.println("Mode: " + findMode(arr));
        System.out.println("Mean: " + findMean(arr));
        System.out.println("Range: " + findRange(arr));
        System.out.println("Standard Deviation: " + findStandardDeviation(arr));
    }
}
