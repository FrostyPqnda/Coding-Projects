import java.util.*;

public class IndexSearchAlgorithm extends LinearSearch
{
    public static void main(String[] args)
    {
        String[][] str = {
            {"Bird", "Dog", "Cat"},
            {"Dragon", "Wolf", "Tiger", "Polar Bear"},
            {"Gecko", "Zebra", "Penguin"}
        };
        
        // Index is found
        System.out.println(array2DSearch(str, "Polar Bear"));

        // Index is not found
        System.out.println(array2DSearch(str, "Lion"));
    }
}
