public class AddStrings 
{
	/*
	 * Method sumStrings adds @param one, @param two, and @param three if they are
	 * all a number value
	 */
	public static int sumStrings(String one, String two, String three)
	{
		int sum = 0;
	    try {
			sum = Integer.parseInt(one) + Integer.parseInt(two) + Integer.parseInt(three);
	    } catch (NumberFormatException nfe) {
			System.out.println("NumberFormatException: \n" + nfe.getMessage()); // Catches any value that is not a number
	    }
	    return sum;
	}
}
