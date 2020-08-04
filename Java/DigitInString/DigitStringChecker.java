import java.util.*;
/**
 * @author brian
 * 
 * DigitStringChecker will check if a given string
 * only contains numbers
 */
public class DigitStringChecker extends DigitChecker
{
	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a text: ");
		String str = input.nextLine();
		
		boolean checkForDigit = digitChecker(str);
		System.out.println(checkForDigit);
		
		if(checkForDigit)
		{
			System.out.println("Contains a digit!");
		}
		else
		{
			System.out.println("Does not contain a digit.");
		}
		
		input.close();
	}

}
