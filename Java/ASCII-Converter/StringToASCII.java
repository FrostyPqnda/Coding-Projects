import java.text.ParseException;
import java.util.Scanner;
/**
 * @author brian
 * 
 * Program that will convert a given string into an
 * ASCII value - standard codes, consisting of 128
 * 7-bit combination, for characters stored in a
 * computer or to be transmitted between computers
 */
public class StringToASCII extends StringConverter
{
	public static void main(String[] args) throws ParseException 
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Enter text: ");
		String convert = input.nextLine();
		
		convertASCII(convert);
		input.close();
	}
}