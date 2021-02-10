import java.util.ArrayList;
import java.util.Scanner;

public class SortSpeedComparator 
{
    static Sorter sort = new Sorter();
    static ArrayListGenerator arr = new ArrayListGenerator();
    static long startTime, endTime, elapsedTime;
    static Scanner scan = new Scanner(System.in);
    static int length;
    static ArrayList<Integer> arrList;

    public static void main(String[] args) {
        arrList = arr.randomArrayList(10);
        arrList.forEach((elem) -> System.out.print(elem + " "));
        System.out.println("\n");
        sort.mergeSort(arrList, arrList.size());
        arrList.forEach((elem) -> System.out.print(elem + " "));

        /*while (true) {
            System.out.print("Choose a test case: ");
            int testCase = scan.nextInt();
            if(testCase == 0) {
                System.out.println("\nTesting for Random ArrayList");
            }
            else if(testCase == 1) {
                System.out.println("Hello!");
                break;
            }
            else if(testCase == 2) {
                System.out.println("Hello!");
                break;
            } 
            else
            {
                System.out.println("NO!");
            }
            
        }*/



        
        scan.close();
    }

    void randomArrayListSpeedTest()
    {
        length = scan.nextInt();
        arrList = arr.randomArrayList(length);
        
        startTime = System.currentTimeMillis();
        sort.bubbleSort(arrList);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("Bubble Sort Time: " + elapsedTime + " ms");
        
        startTime = System.currentTimeMillis();
        sort.selectionSort(arrList);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("Selection Sort Time: " + elapsedTime + " ms");

        startTime = System.currentTimeMillis();
        sort.insertionSort(arrList);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("Insertion Sort Time: " + elapsedTime + " ms");

        startTime = System.currentTimeMillis();
        sort.mergeSort(arrList, arrList.size());
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("Merge Sort Time: " + elapsedTime + " ms");

        startTime = System.currentTimeMillis();
        sort.quickSort(arrList, 0, arrList.size() - 1);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("Quick Sort Time: " + elapsedTime + " ms");
    }
}
