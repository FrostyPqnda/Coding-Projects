import java.util.Scanner;

public class VowelCountConsole extends VowelCount
{

	public static void main(String[] args) 
	{
		int a, e, i, o, u;
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a sentence: ");
		String word = input.nextLine();
		
		a = countVowel(word, 'a');
		e = countVowel(word, 'e');
		i = countVowel(word, 'i');
		o = countVowel(word, 'o');
		u = countVowel(word, 'u');
		
		System.out.println("Your sentence had " + a + " A's, " + e + " E's, " + i + " I's, " + o + " O's, " + "and " + u + " U's.");
		input.close();
	}

}
