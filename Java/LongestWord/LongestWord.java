public class LongestWord
{
    public static void main(String[] args)
    {
        String[] words = {"lizard", "cat", "moose", "duck", "dog", "zebra", "lion", "polar bear", "penguin", "panda", "tiger", "sea lion"};
        System.out.println("The longest word in the array is [" + longestWordInArray(words) + "].");
    }

    // Returns the longest word in the array
    static String longestWordInArray(String[] arr)
    {
        String longestWord = arr[0];
        for(int i  = 0; i < arr.length; i++)
        {
            if(arr[i].length() > longestWord.length())
            {
                longestWord = arr[i];
            }
        }
        return longestWord;
    }
}