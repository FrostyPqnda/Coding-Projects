package VowelCount;
public class VowelCount 
{
	public static int countVowel(String sentence, char vowel)
    {
        int countForVowels = 0;
        String check = "";
        for(int i = 0; i < sentence.length(); i++)
        {
            check += sentence.charAt(i);
            if (check.charAt(i) == vowel)
            {
                countForVowels++;
            }
        }
        return countForVowels;
    }
}