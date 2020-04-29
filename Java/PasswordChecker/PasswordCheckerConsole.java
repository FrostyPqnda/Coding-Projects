import java.util.Scanner;

public class PasswordCheckerConsole extends PasswordChecker
{
	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a password: ");
		String password = input.nextLine();
		Boolean passwordLength = checkPassword(password);
    	System.out.println(passwordLength);
    	input.close();
	}

}
