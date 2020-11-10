/*
 * This is a Java program that will add an extra vowel for any vowel in the user's
 * input.
 */
public class DoubleVowelAppender 
{
	// This is a Public static String method that takes the String text parameter
	// and adds a vowel if there is a vowel in the user's input.
	public static String doubleVowel(String text) 
	{
		String vowel = "";
		for (int i = 0; i < text.length(); i++) 
		{
			char curr = text.charAt(i);
			if (curr == 'a') 
				vowel += "aa"; 
			else if(curr == 'e')
				vowel += "ee";
			else if(curr == 'i')
				vowel += "ii";
			else if(curr == 'o')
				vowel += "oo";
			else if(curr == 'u')
				vowel += "uu";
			else
				vowel += curr;
		}
		return vowel;
	} 
}
