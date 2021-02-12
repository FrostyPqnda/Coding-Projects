/**
 * 
 * @author brian
 * 
 * VowelCount is a java program that will 
 * count the number of times a vowel will
 * appear in a text
 */
public class VowelCount 
{
	public static int countVowel(String sentence, char vowel)
	{
		int countForVowels = 0;
		for(int i = 0; i < sentence.length(); i++)
		    if(sentence.charAt(i) == vowel)
				countForVowels++;
		return countForVowels;
	}
}
