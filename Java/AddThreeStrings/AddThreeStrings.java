package AddThreeStrings;
import java.util.*;
/**
 * 
 * @author FrostyPqnda
 * 
 * AddThreeStrings is a Java program that adds three strings assuming
 * that is a number value
 *
 */
public class AddThreeStrings extends AddStrings
{

	public static void main(String[] args) 
	{
		
		Scanner one = new Scanner(System.in);
		System.out.print("Enter first value: ");
		String inputOne = one.nextLine();
		
		Scanner two = new Scanner(System.in);
		System.out.print("Enter second value: ");
		String inputTwo = two.nextLine();
		
		Scanner three = new Scanner(System.in);
		System.out.print("Enter third value: ");
		String inputThree = three.nextLine();
		
		double addStrings = sumStrings(inputOne, inputTwo, inputThree);
		System.out.println(addStrings);
		
		
		one.close();
		two.close();
		three.close();
		

	}

}
