import java.util.ArrayList;

public class LinearSearch
{
    /**
     * Method arraySearch loops through 
     * the integer array of @param arr for 
     * @param key.
     * 
     * If @param key exist in @param arr
     * the method will return at what index
     */
    public static int arraySearch(int[] arr, int key)
    {
        for(int i = 0; i < arr.length; i++)
            if(arr[i] == key)
                return i;
        return -1;
    }

    /**
     * Method arrayListSearch loops through 
     * the Integer ArrayList of @param arr 
     * for @param key.
     * 
     * If @param key exist in @param arr
     * the method will return at what index
     */
    public static int arrayListSearch(ArrayList<Integer> arr, int key)
    {
        for(int i = 0; i < arr.size(); i++)
            if(arr.get(i) == key)
                return i;
        return -1;
    }

    /**
     * Method array2DSearch loops through
     * the 2D array of integers @param arr to find
     * the index of @param key
     * 
     * If @param key exist in @param arr
     * the method will return at what index
     */
    public static String array2DSearch(int[][] arr, int key)
    {
        for(int row = 0; row < arr.length; row++)
            for(int col = 0; col < arr[row].length; col++)
                if (arr[row][col] == key)
                    return "The index of " + key + " is at row " + row + ", col " + col;
        return "The index of " + key + " was not found";
    }

    /**
     * Method arraySearch loops through 
     * the double array of @param arr for 
     * @param key.
     * 
     * If @param key exist in @param arr
     * the method will return at what index
     */
    public static int arraySearch(double[] arr, double key)
    {
        for(int i = 0; i < arr.length; i++)
            if(arr[i] == key)
                return i;
        return -1;
    }

    /**
     * Method arrayListSearch loops through 
     * the Double ArrayList of @param arr 
     * for @param key.
     * 
     * If @param key exist in @param arr
     * the method will return at what index
     */
    public static int arrayListSearch(ArrayList<Double> arr, double key)
    {
        for(int i = 0; i < arr.size(); i++)
            if(arr.get(i) == key)
                return i;
        return -1;
    }

    /**
     * Method array2DSearch loops through
     * the 2D array of doubles @param arr to find
     * the index of @param key
     * 
     * If @param key exist in @param arr
     * the method will return at what index
     */
    public static String array2DSearch(double[][] arr, double key)
    {
        for(int row = 0; row < arr.length; row++)
            for(int col = 0; col < arr[row].length; col++)
                if (arr[row][col] == key)
                    return "The index of " + key + " is at row " + row + ", col " + col;
        return "The index of " + key + " was not found";
    }
    
    /**
     * Method arraySearch loops through 
     * the String array of @param arr for 
     * @param key.
     * 
     * If @param key exist in @param arr
     * the method will return at what index
     */
    public static int arraySearch(String[] arr, String key)
    {
        for(int i = 0; i < arr.length; i++)
            if(arr[i].equals(key))
                return i;
        return -1;
    }

    /**
     * Method arrayListSearch loops through 
     * the String ArrayList of @param arr 
     * for @param key.
     * 
     * If @param key exist in @param arr
     * the method will return at what index
     */
    public static int arrayListSearch(ArrayList<String> arr, String key)
    {
        for(int i = 0; i < arr.size(); i++)
            if(arr.get(i).equals(key))
                return i;
        return -1;
    }

    /**
     * Method array2DSearch loops through
     * the 2D array of Strings @param arr to find
     * the index of @param key
     * 
     * If @param key exist in @param arr
     * the method will return at what index
     */
    public static String array2DSearch(String[][] arr, String key)
    {
        for(int row = 0; row < arr.length; row++)
            for(int col = 0; col < arr[row].length; col++)
                if(arr[row][col].equals(key))
                    return "The index of " + key + " is at row " + row + ", col " + col;
        return "The index of " + key + " was not found";
    }

    /**
     * Method arraySearch loops through 
     * the Character array of @param arr 
     * for @param key.
     *
     * If @param key exist in @param arr
     * the method will return at what index
     */
    public static int arraySearch(char[] arr, char key)
    {
        for(int i = 0; i < arr.length; i++)
            if(arr[i] == key)
                return i;
        return -1;
    }

    /**
     * Method arrayListSearch loops through 
     * the Character ArrayList of @param arr 
     * for @param key.
     *
     * If @param key exist in @param arr
     * the method will return at what index
     */
    public static int arrayListSearch(ArrayList<Character> arr, char key)
    {
        for(int i = 0; i < arr.size(); i++)
            if(arr.get(i) == key)
                return i;
        return -1;
    }
    
    /**
     * Method array2DSearch loops through
     * the 2D array of Character @param arr to find
     * the index of @param key
     *
     * If @param key exist in @param arr
     * the method will return at what index
     */
    public static String array2DSearch(char[][] arr, char key)
    {
        for(int row = 0; row < arr.length; row++)
            for(int col = 0; col < arr[row].length; col++)
                if(arr[row][col] == key)
                    return "The index of " + key + " is at row " + row + ", col " + col;
        return "The index of " + key + " was not found";
    }

}
