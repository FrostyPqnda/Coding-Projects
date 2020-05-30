import java.util.*;
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
		System.out.println("A palindrome is word/phrase that remains the \nsame when read in reverse. \n");
		
		Scanner input = new Scanner(System.in);
		System.out.print("Check if a word/phrase is a palindrome: ");
		String text = input.nextLine();
		
		boolean palin = palindrome(text);
		System.out.println(palin);
		
		if(palin == true)
		{
			System.out.println(text + " is a palindrome!");
		}
		
		if(palin == false)
		{
			System.out.println(text + " is not a palindrome :(");
		}
		
		input.close();
	}

}
