import java.util.*;

/**
 * 
 * @author brian
 * 
 * FactorialCalculator is a java program that calculates the
 * factorial of any number
 */
public class FactorialCalculator 
{
	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);
		System.out.print("What number would you \nlike to compute the \nfactorial for? ");
		int digit = input.nextInt();
		int product = 1;
		
		for(int i = digit; i > 0; i--)
		{
			product *= i;
		}
		System.out.print("The factorial of " + digit + " is " + product);
		input.close();
	}
}
