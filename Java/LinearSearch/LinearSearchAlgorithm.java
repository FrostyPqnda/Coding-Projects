import java.util.ArrayList;

public class LinearSearchAlgorithm extends Pokemon
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
            if(arr[i].equalsIgnoreCase(key))
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
            if(arr.get(i).equalsIgnoreCase(key))
                return i;
        return -1;
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
     * Method arraySearch loops through 
     * the Pokemon array of @param arr 
     * for @param key.
     * 
     * If @param key exist in @param arr
     * the method will return at what index
     */
    public static int arraySearch(Pokemon[] arr, Pokemon key)
    {
        for(int i = 0; i < arr.length; i++)
            if(arr[i].equals(key))
                return i;
        return -1;
    }
    /**
     * Method arrayListSearch loops through 
     * the Pokemon ArrayList of @param arr 
     * for @param key.
     * 
     * If @param key exist in @param arr
     * the method will return at what index
     */
    public static int arrayListSearch(ArrayList<Pokemon> arr, Pokemon key)
    {
        for(int i = 0; i < arr.size(); i++)
            if(arr.get(i).equals(key))
                return i;
        return -1;
    }

}
