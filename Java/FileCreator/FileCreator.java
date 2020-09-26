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
		Scanner scan = new Scanner(System.in); 
		System.out.print("Name your file: ");
		String fileName = scan.nextLine();
		
		createTextFile(scan, fileName);
		scan.close();
	}

	static void createTextFile(Scanner scan, String fileName)
	{
		// Allows users to name their files 
		// and will set it to .txt format
		fileName = fileName.concat(".txt");

		try 
		{
			File file = new File(fileName); // Creates a File object with the file name created by the user
			
			if(file.createNewFile())
			{
				FileWriter fw = new FileWriter(file); // Creates a FileWriter object to allow users to write what they want to their files
				System.out.println("Type out what you want on your txt file: "); // Asks users what they want to put into the content of their .txt files
				String text = scan.nextLine();
				
				// Writes what the user inputted into the file
				// and closes it after finishing
				fw.write(text);
				fw.close();

				// Closes the scanner object and prints if the file
				// was successfully written and where it was saved
				scan.close();
				System.out.println("SUCCESS! File written to " + fileName);
				System.out.println(file + " was saved in " + file.getAbsolutePath());
			}
			else 
			{
				System.out.println(fileName + " already exists!");
			}
		}
		catch (IOException e) 
		{
			System.out.println("An error occured!");
			e.printStackTrace();
		}
	}
}
