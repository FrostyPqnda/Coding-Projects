import java.util.Scanner;

public class CheckCasesInString extends CheckStringCases
{

	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a word: ");
		String text = input.nextLine();
		
		boolean checkCase = isLowerCase(text);
		System.out.println("Is " + text + " lowercase? " + checkCase);
		input.close();
	}

}
