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
		int left = 0, right = text.length() - 1;
		while(left <= right) {
			if(text.charAt(left) != text.charAt(right))
				return false;
			left++;
			right--;
		}

		return true;
	}
}
