/**
 * @author brian
 * 
 * Method FindPalindrome checks if @param text is
 * a palindrome - word/phrase that will remain the
 * same when read in reverse.
 */
public class FindPalindrome 
{
	public static boolean palindrome(String text)
	{
		String palin = "";
		
		for(int i = text.length() - 1; i >= 0; i--)
		{
			palin += text.charAt(i);
		}
		
		if(palin.equalsIgnoreCase(text))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
