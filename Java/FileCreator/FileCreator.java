import java.util.*;
import java.io.*;

/**
 * 
 * @author brian
 *
 * FileCreator is a simple txt file writer 
 * that users can write to
 */
public class FileCreator 
{
	public static void main(String[] args) 
	{
		try
		{
			// File is being named
			Scanner name_file = new Scanner(System.in);
			System.out.print("Name your file: ");
			String file_name_input = name_file.nextLine();
			String file_name = file_name_input.concat(".txt");
			
			// File content is being created
			FileWriter user_file = new FileWriter(file_name);
			Scanner input = new Scanner(System.in);
			System.out.println("Type out what you want on your txt file: ");
			String text = input.nextLine();
			user_file.write(text);
			
			// File is finished
			user_file.close();
			input.close();
			name_file.close();
			System.out.println("Successfully wrote to the file.");
		}
		catch (IOException e)
		{
			System.out.println("An error occured!");
			e.printStackTrace();
		}
	}
}