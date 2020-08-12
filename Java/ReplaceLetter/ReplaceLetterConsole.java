import java.util.Scanner;

public class ReplaceLetterConsole extends ReplaceLetter
{

	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);

		// Input string
		System.out.print("Enter a word: ");
		String word = input.nextLine();
		
		// char input
		// Letter to be replaced
		System.out.print("Enter a letter to replace: ");
		char letterToReplace = input.next().charAt(0);
		
		// char input
		// Replacing letter
		System.out.print("Enter the replacing letter: ");
		char replacingLetter = input.next().charAt(0);
		
		String replace = replaceLetter(word, letterToReplace, replacingLetter);
		System.out.print("Result: " + replace);
		
		input.close();
	}

}
