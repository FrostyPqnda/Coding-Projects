import java.util.*;

/**
 * 
 * @author brian
 * 
 * FactorialCalculator is a java program that calculates the
 * factorial of any number
 * 
 * A factorial is the product of an integer and all integers before it.
 * n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1.
 */
public class FactorialCalculator 
{
	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);
		System.out.print("What number would you like to compute the factorial for? ");
		int digit = input.nextInt();

		System.out.println("Iteration: The factorial of " + digit + " is " + iterativeFactorial(digit));
		System.out.println("Recursion: The factorial of " + digit + " is " + recursiveFactorial(digit));
		
		input.close();
	}

	/**
	 * Method iterativeFactorial returns the value
	 * of n factorial using iteration (for loop).
	 */
	public static int iterativeFactorial(int n)
	{
		int num = 1;
		for(int s = n; s > 0; s--)
			num *= s;
		return num;
	}

	/**
	 * Method recursiveFactorial returns the value
	 * of n factorial using recusrion.
	 */
	public static int recursiveFactorial(int n)
	{
		if(n == 0)
			return 1;
		else
			return n * recursiveFactorial(n - 1);
	}
}
