/**
 * @author FrostyPqnda
 * 
 * Checks if a given string contains
 * only lower case letters
 */
public class CheckStringCases 
{
	public static boolean isLowerCase(String phrase)
	{
	    char[] charArray = phrase.toCharArray();
	    
	    for(int i = 0; i < phrase.length(); i++)
	        if(Character.isLetter(charArray[i]))
	            return Character.isLowerCase(charArray[i]);
	    return false;
	}
}
