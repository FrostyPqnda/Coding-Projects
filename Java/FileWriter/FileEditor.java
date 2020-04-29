import java.util.*;
import java.io.*;
/**
 * 
 * @author brian
 *
 * FileEditor is a simple txt file writer	
 */
public class FileEditor 
{
	public static void main(String[] args) 
	{
		try
		{
			FileWriter funtest = new FileWriter("UserFile.txt");
			Scanner input = new Scanner(System.in);
			System.out.println("Type out what you want on your txt file: ");
			String text = input.nextLine();
			funtest.write(text);
			funtest.close();
			input.close();
			System.out.println("Successfully wrote to the file.");
		}
		catch (IOException e)
		{
			System.out.println("An error occured!");
			e.printStackTrace();
		}
	}
}