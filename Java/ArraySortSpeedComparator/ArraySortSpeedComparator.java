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
            System.out.println("Enter 1 to test for a Random Array");
            System.out.println("Enter 2 to test for a Reverse Array");
            System.out.println("Enter 3 to test for an Almost Sorted Array");
            System.out.print("\nChoose a test case: ");
            
            int testCase = scan.nextInt();
            if(testCase == 1) 
            {
                randomArraySortSpeedTest();
                break;
            } 
            else if(testCase == 2) 
            {
                reverseArraySortSpeedTest();
                break;
            }
            else if(testCase == 3) 
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
        
        int sort = scan.nextInt();

        System.out.print("\nChoose a sorting algorithm: ");
        System.out.println("\nEnter 1 for Bubble Sort");
        System.out.println("Enter 2 for Selection Sort");
        System.out.println("Enter 3 for Insertion Sort");
        System.out.println("Enter 4 for Merge Sort");
        System.out.println("Enter 5 for Quick Sort");

        while(true)
        {
            if(sort == 1)
            {
                bubbleSortTime(randomArr);
                break;
            }
            else if(sort == 2)
            {
                selectionSortTime(randomArr);
                break;
            }
            else if(sort == 3)
            {
                insertionSortTime(randomArr);
                break;
            }
            else if(sort == 4)
            {
                mergeSortTime(randomArr);
                break;
            }
            else if(sort == 5)
            {
                quickSortTIme(randomArr);
                break;
            }
            else
            {
                System.out.println("Invalid input!");
            }
        }
    }

    static void reverseArraySortSpeedTest()
    {
        int[] reverseArr = arr.reverseArray(length);
        System.out.println("Testing for Reverse Array");
        
        int sort = scan.nextInt();

        System.out.print("\nChoose a sorting algorithm: ");
        System.out.println("\nEnter 1 for Bubble Sort");
        System.out.println("Enter 2 for Selection Sort");
        System.out.println("Enter 3 for Insertion Sort");
        System.out.println("Enter 4 for Merge Sort");
        System.out.println("Enter 5 for Quick Sort");

        while(true)
        {
            if(sort == 1)
            {
                bubbleSortTime(reverseArr);
                break;
            }
            else if(sort == 2)
            {
                selectionSortTime(reverseArr);
                break;
            }
            else if(sort == 3)
            {
                insertionSortTime(reverseArr);
                break;
            }
            else if(sort == 4)
            {
                mergeSortTime(reverseArr);
                break;
            }
            else if(sort == 5)
            {
                quickSortTIme(reverseArr);
                break;
            }
            else
            {
                System.out.println("Invalid input!");
            }
        }
    }

    static void almostSortedArraySortSpeedTest() 
    {
        int[] almostSortedArr = arr.almostSortedArrayList(length);
        System.out.println("Testing for Almost Sorted ArrayList");
        
        int sort = scan.nextInt();

        System.out.print("\nChoose a sorting algorithm: ");
        System.out.println("\nEnter 1 for Bubble Sort");
        System.out.println("Enter 2 for Selection Sort");
        System.out.println("Enter 3 for Insertion Sort");
        System.out.println("Enter 4 for Merge Sort");
        System.out.println("Enter 5 for Quick Sort");

        while(true)
        {
            if(sort == 1)
            {
                bubbleSortTime(almostSortedArr);
                break;
            }
            else if(sort == 2)
            {
                selectionSortTime(almostSortedArr);
                break;
            }
            else if(sort == 3)
            {
                insertionSortTime(almostSortedArr);
                break;
            }
            else if(sort == 4)
            {
                mergeSortTime(almostSortedArr);
                break;
            }
            else if(sort == 5)
            {
                quickSortTIme(almostSortedArr);
                break;
            }
            else
            {
                System.out.println("Invalid input!");
            }
        }
    }

    static void bubbleSortTime(int[] arr)
    {
        startTime = System.currentTimeMillis();
        sort.bubbleSort(arr);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("\nBubble Sort Time: " + elapsedTime + " ms");
    }

    static void selectionSortTime(int[] arr)
    {
        startTime = System.currentTimeMillis();
        sort.selectionSort(arr);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("\nSelection Sort Time: " + elapsedTime + " ms");
    }

    static void insertionSortTime(int[] arr)
    {
        startTime = System.currentTimeMillis();
        sort.insertionSort(arr);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("\nInsertion Sort Time: " + elapsedTime + " ms");
    }

    static void mergeSortTime(int[] arr)
    {
        startTime = System.currentTimeMillis();
        sort.mergeSort(arr, arr.length);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("\nMerge Sort Time: " + elapsedTime + " ms");
    }

    static void quickSortTIme(int[] arr)
    {
        startTime = System.currentTimeMillis();
        sort.quickSort(arr, 0, arr.length - 1);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("\nQuick Sort Time: " + elapsedTime + " ms");
    }
}