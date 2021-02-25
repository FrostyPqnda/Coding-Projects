public class ArrayStats extends Statistics
{
    public static void main(String[] args)
    {
        int[] arr = randomArray(10);
        System.out.print("Data Set = { ");
        for(int i : arr)
            System.out.print(i + " ");
        System.out.println("}");
        System.out.println("Median: " + findMedian(arr));
        System.out.println("Mode: " + findMode(arr));
        System.out.println("Mean: " + findMean(arr));
        System.out.println("Range: " + findRange(arr));
        System.out.println("Standard Deviation: " + findStandardDeviation(arr));
    }

    static int[] randomArray(int size)
    {
        int[] arr = new int[size];
        for(int i = 0; i < arr.length; i++)
        {
            int value = (int)(Math.random() * 91) + 10;
            arr[i] = value;
        }
        return arr;
    }
}
