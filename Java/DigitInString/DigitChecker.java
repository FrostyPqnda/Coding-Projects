public class DigitChecker 
{
	/**
	 * Method digitChecker checks if there is a digit
	 * in @param str
	 */
	public static boolean digitChecker(String str)
	{
		for(int i = 0; i < str.length(); i++)
		{
			try
			{
				int num = Integer.parseInt(str);
				
				if(Character.isDigit(num))
				{
					return true;
				}
				
			}
			catch (NumberFormatException nfe)
		    {
		      return false;
		    }
		}
		return true;
	}
}
