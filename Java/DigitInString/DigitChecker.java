public class DigitChecker 
{
	/**
	 * Method digitChecker checks if there is a digit
	 * in @param str
	 */
	public static boolean digitChecker(String str)
	{
		String text = "";

		char[] digit = text.toCharArray();

		for(char c : digit)
		{
			if(Character.isDigit(c))
			{
				return true;
			}
			else
			{
				return false;
			}
		}

		return true;
	}
}
