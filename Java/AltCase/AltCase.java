/*
 * This is a Java program that will alternate the letter casing
 * of the user's string input.
 */
public class AltCase 
{
	/*
	 * This is a static string method
	 * that changes alternates the casing
	 * of a word/sentence you input/
	 */
	public static String altCase(String text)
	{
	    String returnAltCase = "";
	    for(int i = 0; i < text.length(); i++)
	    {
	        char curCase = text.charAt(i);
	        if(i % 2 == 0)
	        {
	            returnAltCase += Character.toUpperCase(curCase);
	        }
	        else
	        {
	            returnAltCase += Character.toLowerCase(curCase);
	        }
	    }
	    return returnAltCase;
	}

}