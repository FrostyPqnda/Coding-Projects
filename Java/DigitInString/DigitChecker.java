public class DigitChecker 
{
	/**
	 * Method digitChecker checks if there is a digit
	 * in @param str
	 */
	public static boolean digitChecker(String str)
	{
		char[] digit = str.toCharArray();
		boolean containsDigit = false;

		if(!(str == null && str.isEmpty()))
			for(char c : digit)
				if(containsDigit = Character.isDigit(c))
					break;
		return containsDigit;
	}
}
