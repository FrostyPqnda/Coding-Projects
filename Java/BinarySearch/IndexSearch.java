public class IndexSearch extends BinarySearch
{
    public static void main(String[] args)
    {
        //int[] arr = {324, 325, 2345, 435, 436, 123, 325, 465, 14, 235};
        //double[] arr = {54.234, 5.5, 345.43, 43.6, 324.6};
        //char[] arr = {'a', 'd', 'r', 'b', 'h', 'g', 'm', 'w'};
        String[] arr = {"Lion", "Gecko", "Ape", "Mouse", "Rabbit", "Panther", "Python"};
        System.out.println("Unsorted");
        for(String item : arr)
            System.out.print(item + " ");
        System.out.println("\n\nSorted");
        MergeSortAlgorithm.mergeSort(arr, arr.length);
        for(String item : arr)
            System.out.print(item + " ");
        int index = arrayBinarySearch(arr, 0, arr.length - 1, "Lion");
        System.out.println("\n\nIndex found at position " + index);
    }
}