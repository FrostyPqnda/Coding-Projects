public class RecombineString
{
    public static void main(String[] args)
    {
        String first = "apple";
        String second = "pear";
        System.out.println("Recombined word: " + recombine(first, second));
    }

    // Takes the first half of the first string and the second half of the second string and combines them
    static String recombine(String firstWord, String secondWord)
    {
        String first = "";
        for(int i = 0; i < firstWord.length() / 2; i++)
        {
            first += firstWord.charAt(i);
        }
        String second = "";
        for(int i = secondWord.length() / 2; i < secondWord.length(); i++)
        {
            second += secondWord.charAt(i);
        }
        return first + second;
    }
}