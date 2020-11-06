import java.util.Scanner;

public class PasswordCheckerConsole extends PasswordChecker
{
	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a password: ");
		String password = input.nextLine();
		System.out.println("Valid password? " + checkPassword(password));
		input.close();
	}
}
