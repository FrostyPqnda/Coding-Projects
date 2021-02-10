import java.util.ArrayList;
public class Sorter
{
    public void bubbleSort(ArrayList<Integer> arrList)
    {
        for(int i = 0; i < arrList.size() - 1; i++) 
            for(int x = 0; x < arrList.size() - i - 1; x++) 
                if(arrList.get(x) > arrList.get(x + 1)) 
                    swap(arrList, x, x+1);
    }

    public void selectionSort(ArrayList<Integer> arrList)
    {
        for(int i = 0; i < arrList.size() - 1; i++) 
        {
            // Find the minimum index in the array
            int minIndex = i; 
            for(int x = i + 1; x < arrList.size(); x++) 
                if(arrList.get(x) < arrList.get(minIndex)) 
                    minIndex = x;
            swap(arrList, minIndex, i);
        }
    }

    public void insertionSort(ArrayList<Integer> arrList)
    {
        for(int i = 1; i < arrList.size(); ++i)
        {   
            int key = arrList.get(i);
            int x = (i - 1);
            while(x >= 0 && arrList.get(x) > key)
            {
                arrList.set(x + 1, arrList.get(x));
                x--;
            }
            arrList.set(x + 1, key);
        }
    }

    public void mergeSort(ArrayList<Integer> arrList, int length)
    {
        if(length < 2)
            return;

        int mid = length / 2;
        ArrayList<Integer> left = new ArrayList<Integer>(mid);
        ArrayList<Integer> right = new ArrayList<Integer>(length - mid);

        for(int i = 0; i < mid; i++)
            left.set(i, arrList.get(i));
        
        for(int i = mid; i < length; i++)
            right.set(i - mid, arrList.get(i));
        
        mergeSort(left, mid);
        mergeSort(right, length - mid);
        merge(arrList, left, right);
    }

    void merge(ArrayList<Integer> curr, ArrayList<Integer> left, ArrayList<Integer> right)
    {
        int l = 0; int r = 0; int i = 0;    
        while(l < left.size() && r < right.size())
        {
            if(left.get(l) <= right.get(r))
                curr.set(i++, left.get(l++));
            else
                curr.set(i++, right.get(i++));
        }

        while(i < left.size()) {
            curr.set(i++, left.get(l++));
        }

        while(r < right.size()) {
            curr.set(i++, right.get(i++));
        }
    }

    public void quickSort(ArrayList<Integer> arrList, int low, int high)
    {
        if(low < high)
        {
            int pi = partition(arrList, low, high);
            quickSort(arrList, low, pi - 1);
            quickSort(arrList, pi + 1, high);
        }
    }

    int partition(ArrayList<Integer> arrList, int low, int high)
    {
        int i = (low - 1);
        for(int x = low; x < high; x++)
        {
            if(arrList.get(x) < arrList.get(high)) 
            {
                i++;
                swap(arrList, x, i);
            } 
        }
        swap(arrList, i+1, high);
        return (i + 1);
    }

    void swap(ArrayList<Integer> arrList, int x, int y)
    {
        int swap = arrList.get(x);
        arrList.set(x, arrList.get(y));
        arrList.set(y, swap);
    }
}