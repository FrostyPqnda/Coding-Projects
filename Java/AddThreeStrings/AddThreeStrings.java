import java.util.Scanner;
/**
 * 
 * @author FrostyPqnda
 * 
 * AddThreeStrings is a Java program that adds three strings assuming
 * that it is a number value
 *
 */
public class AddThreeStrings extends AddStrings
{

	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);

		System.out.print("Enter first value: ");
		String inputOne = input.nextLine();
		
		System.out.print("Enter second value: ");
		String inputTwo = input.nextLine();
		
		System.out.print("Enter third value: ");
		String inputThree = input.nextLine();
		
		int addStrings = sumStrings(inputOne, inputTwo, inputThree);
		System.out.println(addStrings);

		input.close();
	}
}
