import java.util.Arrays;
public class StringConverter 
{
	/**
	 * Method convertASCII will convert @param str
	 * into an ASCII value
	 */
	public static void convertASCII(String str)
	{
		try
		{
			String convertText = str;
			byte[] c = convertText.getBytes("US-ASCII");
			
			System.out.println("The ASCII value of " + convertText + " is: ");
			System.out.println(Arrays.toString(c));
		}
		catch(java.io.UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
	}
}
