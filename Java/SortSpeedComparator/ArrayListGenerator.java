import java.util.ArrayList;

public class ArrayListGenerator 
{
    public ArrayList<Integer> randomArrayList(int length)
    {
        ArrayList<Integer> arrList = new ArrayList<Integer>();
        for(int i = 0; i < length; i++)
        {
            int randVal = (int)(Math.random() * length);
            arrList.add(randVal);
        }
        return arrList;
    }

    public ArrayList<Integer> reverseArrayList(int length)
    {   
        ArrayList<Integer> arrList = new ArrayList<Integer>();
        int count = length;
        for(int i = 0; i < length; i++) 
        {
            arrList.add(i, count);
            count--;
        }
        return arrList;
    }

    public ArrayList<Integer> almostSortedArrayList(int length)
    {
        ArrayList<Integer> arrList = new ArrayList<Integer>(length);
        for(int i = 0; i < length; i++)
            arrList.add(i, i+1);
        int swap = arrList.get(arrList.size() - 1);
        arrList.set(arrList.size() - 1, arrList.get(arrList.size() - 2));
        arrList.set(arrList.size() - 2, swap);
        return arrList;
    }
}
