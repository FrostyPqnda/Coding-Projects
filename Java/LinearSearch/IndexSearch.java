import java.util.ArrayList;

public class IndexSearch extends LinearSearchAlgorithm
{
    public static void main(String[] args)
    {
        String[][] str = {
            {"Bird", "Dog", "Cat"},
            {"Dragon", "Wolf", "Tiger"},
            {"Gecko", "Zebra", "Penguin"}
        };
        
        // Index is found
        System.out.println(array2DSearch(str, "Gecko"));

        // Index is not found
        System.out.println(array2DSearch(str, "Lion"));
    }
}
