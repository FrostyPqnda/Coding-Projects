import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

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
			Scanner input = new Scanner(System.in);
			// File is being named
			System.out.print("Name your file: ");
			String file_name_input = input.nextLine();
			String file_name = file_name_input.concat(".txt");
			
			// File content is being created
			FileWriter user_file = new FileWriter(file_name);
			System.out.println("Type out what you want on your txt file: ");
			String text = input.nextLine();
			user_file.write(text);
			
			// File is finished
			user_file.close();
			input.close();
			System.out.println("SUCCESS! File written to " + file_name);
		}
		catch (IOException e)
		{
			System.out.println("An error occured!");
			e.printStackTrace();
		}
	}
}
