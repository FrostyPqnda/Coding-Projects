import java.util.Scanner;

public class ReplaceLetterConsole extends ReplaceLetter
{

	public static void main(String[] args) 
	{
		// Input string
		Scanner inputWord = new Scanner(System.in);
		System.out.print("Enter a word: ");
		String word = inputWord.nextLine();
		
		// char input
		// Letter to be replaced
		Scanner inputLetterToReplace = new Scanner(System.in);
		System.out.print("Enter a letter to replace: ");
		char letterToReplace = inputLetterToReplace.next().charAt(0);
		
		// char input
		// Replacing letter
		Scanner inputReplacingLetter = new Scanner(System.in);
		System.out.print("Enter the replacing letter: ");
		char replacingLetter = inputReplacingLetter.next().charAt(0);
		
		String replace = replaceLetter(word, letterToReplace, replacingLetter);
		System.out.print("Result: " + replace);
		
		inputWord.close();
		inputLetterToReplace.close();
		inputReplacingLetter.close();
	}

}
