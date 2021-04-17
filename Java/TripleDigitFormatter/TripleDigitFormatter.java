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
    /**
     * Method tripleDigitFormatter formats
     * @param num as a triple digit if it
     * is in range of 0 to 99 and returns it.
     */
    public static String tripleDigitFormatter(int num)
    {
        String str = "";
        if(num >= 0)
            str += String.format("%03d", num);
        else if(num >= 100)
            str += Integer.toString(num);

        return "Result: " + str + "\n";
    }

    /**
     * Method tripleDigitFormatter formats
     * @param letter as an ASCII value and
     * returns it in triple digits
     */
    public static String tripleDigitFormatter(char letter)
    {
        String str = "";
        int ascii = 0;

        if(Character.isLetter(letter))
        {
            ascii = letter;
            str += String.format("%03d", ascii);
        }

        return "Original: " + letter + "\nResult: " + str;
    }
}