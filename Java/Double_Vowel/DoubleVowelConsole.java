package Double_Vowel;
import java.util.Scanner;

public class DoubleVowelConsole extends Double_Vowel
{

	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a word/sentence: ");
		String word = input.nextLine();
		String output = doubleVowel(word);
		System.out.println("Result: " + output);
		input.close();
	}

}
