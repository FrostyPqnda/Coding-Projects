import java.util.Scanner;

public class GameTester
{

	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);

		// Asks user for the title of their game
		System.out.print("Enter title of game: ");
		String name = input.nextLine();
		
		// Asks user for the hero of their game
		System.out.print("Enter name of protagonist: ");
		String hero = input.nextLine();
		
		// Asks user for the villain of their game
		System.out.print("Enter name of antagonist: ");
		String villian = input.nextLine();
		
		// Asks user for the location their game takes place
		System.out.print("Enter the world in which the game takes place: ");
		String bg = input.nextLine();
		
		VideoGame VGD = new VideoGame(name, hero, villian, bg);
		System.out.println(VGD);
		
		input.close();
	}

}
