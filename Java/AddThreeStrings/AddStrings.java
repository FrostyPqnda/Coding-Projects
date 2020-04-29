public class AddStrings 
{
	/*
	 * Method sumStrings adds @param one, @param two, and @param three if they are
	 * all a number value
	 */
	public static double sumStrings(String one, String two, String three)
	{
	    try 
	    {
	        double f = Double.parseDouble(one);
	        double s = Double.parseDouble(two);
	        double t = Double.parseDouble(three);
	        
	        double sum = f + s + t;
	        return sum;
	    }
	    // Catches any value that is not a number
	    catch (NumberFormatException nfe)
	    {
	      System.out.println("NumberFormatException: \n" + nfe.getMessage());
	    }
	    
	    return 0;
	}
}
