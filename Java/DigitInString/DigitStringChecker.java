import java.util.Scanner;
/**
 * @author brian
 * 
 * DigitStringChecker will check if a given string
 * contains a number
 */
public class DigitStringChecker extends DigitChecker
{
	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a text: ");
		String str = input.nextLine();
		
		boolean checkForDigit = digitChecker(str);
		System.out.println("Contains a digit? " + checkForDigit);
		
		input.close();
	}

}
