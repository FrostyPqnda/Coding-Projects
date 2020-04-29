import java.util.*;
import java.io.*;

/**
 * 
 * @author brian
 *
 * FileEditor is a simple txt file writer 
 * that users can write to
 */
public class FileEditor 
{
	public static void main(String[] args) 
	{
		try
		{
			FileWriter user_file = new FileWriter("UserFile.txt");
			Scanner input = new Scanner(System.in);
			System.out.println("Type out what you want on your txt file: ");
			String text = input.nextLine();
			user_file.write(text);
			user_file.close();
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