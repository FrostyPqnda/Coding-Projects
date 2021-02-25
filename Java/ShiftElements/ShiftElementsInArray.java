public class ShiftElementsInArray
{
    public static void main(String[] args)
    {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println("Original Array:");
        for(int item : arr)
            System.out.print(item + " ");
        System.out.println("\n\nArray shifted to the right:");
        shiftRight(arr);
        for(int item : arr)
            System.out.print(item + " ");
    }

    // Shifts the elements to the left
    static void shiftLeft(int[] arr)
    {
        int temp = arr[0];
        for(int i = 0; i < arr.length - 1; i++)
            arr[i] = arr[i + 1];
        arr[arr.length - 1] = temp;
    }

    // Shifts the elements to the right
    static void shiftRight(int[] arr)
    {
        int temp = arr[arr.length - 1];
        for(int i = arr.length - 1; i > 0; i--)
            arr[i] = arr[i - 1];
        arr[0] = temp;
    }
}