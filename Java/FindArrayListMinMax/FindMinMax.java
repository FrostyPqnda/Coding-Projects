import java.util.ArrayList;
import java.util.Collections;

/**
 * Java program that finds the smallest integer
 * in an ArrayList
 */
public class FindMinMax
{
    /**
     * Method collectionsFindMinimum uses the Collections class to sort @param numbers 
     * and find the smallest integer in the ArrayList
     */
    public static int collectionsFindMinimum(ArrayList<Integer> numbers)
    {
        if(numbers.size() == 1 || numbers == null)
            return numbers.get(0);
        Collections.sort(numbers);
        // Removes the highest number in the ArrayList until only one element is left.
        for(int i = numbers.size() - 1; i > 0; i--)
            numbers.remove(i);
        return collectionsFindMinimum(numbers);
    }

    /**
     * Method collectionsFindMaximum uses the Collections class to sort @param numbers 
     * and find the highest integer in the ArrayList
     */
    public static int collectionsFindMaximum(ArrayList<Integer> numbers)
    {
        if(numbers.size() == 1 || numbers == null)
            return numbers.get(0);
        Collections.sort(numbers);
        // Removes the lowest number in the ArrayList until only one element is left.
        for(int i = 0; i < numbers.size() - 1; i++)
            numbers.remove(i);
        return collectionsFindMaximum(numbers);
    }

    /**
     * Method nestedFindMinimum uses a nested for-loop to sort @param numbers
     * and find the smallest integer in the ArrayList
     */
    public static int nestedLoopFindMinimum(ArrayList<Integer> numbers)
    {
        if(numbers.size() == 1 || numbers == null)
            return numbers.get(0);
        // Sorts the ArrayList in descending order using a nested for-loop
        for(int x = 0; x < numbers.size(); x++)
            for(int y = numbers.size() - 1; y > x; y--)
                if(numbers.get(x) > numbers.get(y)) {
                    numbers.set(x, numbers.get(y)); 
                    numbers.set(y, numbers.get(x));
                }
        // Removes the highest number in the ArrayList until only one element is left.
        for(int i = numbers.size() - 1; i > 0; i--)
            numbers.remove(i);
        return nestedLoopFindMinimum(numbers);
    }

    /**
     * Method nestedLoopFindMaximum uses a nested for-loop to sort @param numbers
     * and find the largest integer in the ArrayList
     */
    public static int nestedLoopFindMaximum(ArrayList<Integer> numbers)
    {
        if(numbers.size() == 1 || numbers == null)
            return numbers.get(0);
        // Sorts the ArrayList in ascending order using a nested for-loop
        for(int x = 0; x < numbers.size(); x++)
            for(int y = numbers.size() - 1; y > x; y--)
                if(numbers.get(x) < numbers.get(y)) {
                    numbers.set(x, numbers.get(y));
                    numbers.set(y, numbers.get(x));
                }
        // Removes the smallest number in the ArrayList until only one element is left.
        for(int i = numbers.size() - 1; i > 0; i--)
            numbers.remove(i);
        return nestedLoopFindMaximum(numbers);
    }

    
}