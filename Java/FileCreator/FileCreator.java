import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
/**
 * 
 * @author FrostyPqnda
 *
 * FileCreator is a simple txt file writer 
 * that users can write to
 */
public class FileCreator 
{
	public static void main(String[] args) 
	{
		// Creates a scanner object for user input
		// and asks users to name their files.
		Scanner input = new Scanner(System.in); 
		System.out.print("Name your file: ");
		
		// Allows users to name their files 
		// and will convert it to .txt format
		String fileInput = input.nextLine();
		String fileName = fileInput.concat(".txt");

		try
		{	
			File path = new File("D:\\Coding-Projects\\Java\\FileCreator\\Files"); // Sets the directory where the file will be save
			File file = new File(path, fileName); // Creates a new File object with the parent directory and the .txt file the user created
			FileWriter fw = new FileWriter(file); // Creates a FileWriter object to allow users to write what they want to their files
			
			// Asks users what they want to put into the content of their
			// .txt files
			System.out.println("Type out what you want on your txt file: ");
			String text = input.nextLine();
			
			// Writes what the user inputted into the file
			// and closes it after finishing
			fw.write(text);
			fw.close();
			
			// Closes the scanner object and prints if the file
			// was successfully written and where it was saved
			input.close();
			System.out.println("SUCCESS! File written to " + fileName);
			System.out.println(fileName + " was saved in " + path.getPath());
		}
		catch (IOException e)
		{
			System.out.println("An error occured!");
			e.printStackTrace();
		}
	}
}
