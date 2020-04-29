/*
 * This is a Java program that takes in a string of binary numbers
 * and convert it into a string or number.
 */
public class BinaryTranslator 
{
	/*
	 * Public static string methood that converts
	 * a binary string into my name.
	 */
	public static String binaryToText(String binary)
	{
	    String ret = "";
	    for( int i = 0; i < binary.length(); i+=8)
	    {
	        String s = binary.substring(i, i+8);
	        int dec = binaryToDecimal(s);
	        char c = (char)dec;
	        ret += String.valueOf(c);
	    }
	    return ret;
	}
	/*
	 * Public static int method that takes in a binary
	 * string as a parameter and converts it into a
	 * decimal.
	 */
	public static int binaryToDecimal(String binaryString)
	{
	    String[] digits = binaryString.split("");
	    int res = 0;
	    int exp = digits.length-1;
	    for(int i = 0; i < digits.length; i++)
	    {
	        int curDigit = Integer.valueOf(digits[i]).intValue();
	        res += curDigit * Math.pow(2, exp);
	        exp --;
	    }
	    return res;
	}
}
