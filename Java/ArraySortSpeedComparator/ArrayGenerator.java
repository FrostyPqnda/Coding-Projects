public class ArrayGenerator 
{
    public int[] randomArray(int length)
    {
        int[] arr = new int[length];
        for(int i = 0; i < length; i++)
        {
            int randVal = (int)(Math.random() * length);
            arr[i] = randVal;
        }
        return arr;
    }

    public int[] reverseArray(int length)
    {   
        int[] arr = new int[length];
        for(int i = 0; i < length; i++) 
        {
            arr[i] = i;
        }
        for(int i = 0; i < arr.length / 2; i++)
        {
            int temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
        return arr;
    }

    public int[] almostSortedArrayList(int length)
    {
        int[] arr = new int[length];
        for(int i = 0; i < length; i++) 
        {
            arr[i] = i + 1;
        }
        int swap = arr[arr.length - 1];
        arr[arr.length - 1] = arr[arr.length - 2];
        arr[arr.length - 2] = swap;
        return arr;
    }
}
