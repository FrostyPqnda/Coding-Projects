/**
 * @author FrostyPqnda
 * 
 * Formats a given integer value 
 * between the range of 0 to 99
 * as a triple digit.
 * 
 * Examples: 5 -> 005, 50 -> 050
 */
public class TripleDigitFormatter
{
    public static String tripleDigitFormatter(int num)
    {
        String str = "";
        if(num >= 0 && num <= 99)
            str += String.format("%03d", num);
        else if(num >= 100)
            str += Integer.toString(num);

        return "Original: " + num + "\nNew: " + str;
    }
}