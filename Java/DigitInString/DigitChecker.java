public class DigitChecker 
{
	/**
	 * Method digitChecker checks if there is a digit
	 * in @param str
	 */
	public static boolean digitChecker(String str)
	{
		char[] charArray = str.toCharArray();
		boolean containsDigit = false;

		for(char c : charArray)
		{
			if(Character.isDigit(c))
			{
				containsDigit = true;
			}
		}

		return containsDigit;
	}
}
