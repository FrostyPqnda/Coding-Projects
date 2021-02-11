import java.util.Scanner;

public class ArraySortSpeedComparator 
{
    static Sorter sort = new Sorter();
    static ArrayGenerator arr = new ArrayGenerator();
    static long startTime, endTime, elapsedTime;
    static Scanner scan = new Scanner(System.in);
    static int length = 50000;

    public static void main(String[] args) 
    {
        while (true) 
        {
            System.out.println("Enter 0 to test for a Random Array");
            System.out.println("Enter 1 to test for a Reverse Array");
            System.out.println("Enter 2 to test for an Almost Sorted Array");
            System.out.print("\nChoose a test case: ");
            
            int testCase = scan.nextInt();
            if(testCase == 0) 
            {
                randomArraySortSpeedTest();
                break;
            } 
            else if(testCase == 1) 
            {
                reverseArraySortSpeedTest();
                break;
            }
            else if(testCase == 2) 
            {
                almostSortedArraySortSpeedTest();
                break;
            } 
            else 
            {
                System.out.println("Invalid input!");
            } 
        }
        scan.close();
    }

    static void randomArraySortSpeedTest()
    {
        int[] randomArr = arr.randomArray(length);
        System.out.println("\nTesting for Random Array");
        
        startTime = System.currentTimeMillis();
        sort.bubbleSort(randomArr);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("\nBubble Sort Time: " + elapsedTime + " ms");
        
        startTime = System.currentTimeMillis();
        sort.selectionSort(randomArr);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("Selection Sort Time: " + elapsedTime + " ms");

        startTime = System.currentTimeMillis();
        sort.insertionSort(randomArr);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("Insertion Sort Time: " + elapsedTime + " ms");

        startTime = System.currentTimeMillis();
        sort.mergeSort(randomArr, randomArr.length);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("Merge Sort Time: " + elapsedTime + " ms");

        startTime = System.currentTimeMillis();
        sort.quickSort(randomArr, 0, randomArr.length - 1);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("Quick Sort Time: " + elapsedTime + " ms");
    }

    static void reverseArraySortSpeedTest()
    {
        int[] reverseArr = arr.reverseArray(length);
        System.out.println("Testing for Reverse Array");
        
        startTime = System.currentTimeMillis();
        sort.bubbleSort(reverseArr);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("\nBubble Sort Time: " + elapsedTime + " ms");
        
        startTime = System.currentTimeMillis();
        sort.selectionSort(reverseArr);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("Selection Sort Time: " + elapsedTime + " ms");

        startTime = System.currentTimeMillis();
        sort.insertionSort(reverseArr);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("Insertion Sort Time: " + elapsedTime + " ms");

        startTime = System.currentTimeMillis();
        sort.mergeSort(reverseArr, reverseArr.length);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("Merge Sort Time: " + elapsedTime + " ms");

        startTime = System.currentTimeMillis();
        sort.quickSort(reverseArr, 0, reverseArr.length - 1);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("Quick Sort Time: " + elapsedTime + " ms");
    }

    static void almostSortedArraySortSpeedTest() 
    {
        int[] almostSortedArr = arr.almostSortedArrayList(length);
        System.out.println("Testing for Almost Sorted ArrayList");
        
        startTime = System.currentTimeMillis();
        sort.bubbleSort(almostSortedArr);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("\nBubble Sort Time: " + elapsedTime + " ms");
        
        startTime = System.currentTimeMillis();
        sort.selectionSort(almostSortedArr);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("Selection Sort Time: " + elapsedTime + " ms");

        startTime = System.currentTimeMillis();
        sort.insertionSort(almostSortedArr);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("Insertion Sort Time: " + elapsedTime + " ms");

        startTime = System.currentTimeMillis();
        sort.mergeSort(almostSortedArr, almostSortedArr.length);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("Merge Sort Time: " + elapsedTime + " ms");

        startTime = System.currentTimeMillis();
        sort.quickSort(almostSortedArr, 0, almostSortedArr.length - 1);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("Quick Sort Time: " + elapsedTime + " ms");
    }
}
