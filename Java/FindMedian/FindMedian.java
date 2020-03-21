package FindMedian;

/*
 * This is a Java program that finds the median number in an array
 */
import java.util.Arrays;

public class FindMedian 
{
	/*
	 * Public static double method
	 * takes an array of numbers and
	 * calculates to find the median.
	 */
	public static double median(int[] arr)
	{
		Arrays.sort(arr);
		double median;
		if(arr.length % 2 != 0)
	    {
	        return arr[arr.length / 2];
	    }
	    else if(arr.length % 2 == 0)
	    {
	        double evenMedian = arr[arr.length / 2] + arr[arr.length / 2 -1];
	        median = ((double) evenMedian) / 2;
	        return median;
	    }
	    else
	    {
	        return 0;
	    }
	}
}



