import java.util.Scanner;
/**
 * @author brian
 *
 * Checks if the user's input [a word/phrase] is a palindrome
 * - a word/phrase that remain the same when read in reverse
 */
public class PalindromeChecker extends FindPalindrome
{
	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Check if a word/phrase is a palindrome: ");
		String text = input.nextLine();
		
		boolean palin = palindrome(text);
		System.out.println(palin);
		
		if(palin)
		{
			System.out.println(text + " is a palindrome!");
		}
		else
		{
			System.out.println(text + " is not a palindrome :(");
		}
		
		input.close();
	}
}
