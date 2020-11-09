/**
 * 
 * @author brian
 *
 * ReplaceLetter is a java program that will replace a letter
 * from a word with another letter
 */
public class ReplaceLetter 
{
	public static String replaceLetter(String word, char letterToReplace, char replacingLetter)
	{
		String text = "";
		for(int i = 0; i < word.length(); i++)
		    if(word.charAt(i) == letterToReplace)
				text += replacingLetter;
		    else
				text += word.charAt(i);
		return text;
	}
}
