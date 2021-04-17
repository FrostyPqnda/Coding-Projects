public class DigitChecker 
{
	/**
	 * Method digitChecker checks if there is a digit
	 * in @param str
	 */
	public static boolean digitChecker(String str)
	{
		for(char c : str.toCharArray())
		{
			if(Character.isDigit(c))
			{
				return true;
			}
		}
		return false;
	}
}
