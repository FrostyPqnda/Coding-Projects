import java.util.Scanner;

public class AltCaseConsole extends AltCase
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a word/sentence: ");
		String word = input.nextLine();
		String output = altCase(word);
		System.out.println("Result: " + output);
		input.close();
	}
}
