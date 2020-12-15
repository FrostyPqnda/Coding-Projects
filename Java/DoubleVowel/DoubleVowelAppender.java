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
			if (text.charAt(i) == 'a') 
				vowel += "aa"; 
			else if(text.charAt(i) == 'e')
				vowel += "ee";
			else if(text.charAt(i) == 'i')
				vowel += "ii";
			else if(text.charAt(i) == 'o')
				vowel += "oo";
			else if(text.charAt(i) == 'u')
				vowel += "uu";
			else
				vowel += text.charAt(i);
		return vowel;
	} 
}
