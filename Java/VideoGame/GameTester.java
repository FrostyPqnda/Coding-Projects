import java.util.Scanner;

public class GameTester
{

	public static void main(String[] args) 
	{
		// Asks user for the title of their game
		Scanner logo = new Scanner(System.in);
		System.out.print("Enter title of game: ");
		String name = logo.nextLine();
		
		// Asks user for the hero of their game
		Scanner savior = new Scanner(System.in);
		System.out.print("Enter name of protagonist: ");
		String hero = savior.nextLine();
		
		// Asks user for the villain of their game
		Scanner badGuy = new Scanner(System.in);
		System.out.print("Enter name of antagonist: ");
		String villian = badGuy.nextLine();
		
		// Asks user for the location their game takes place
		Scanner world =  new Scanner(System.in);
		System.out.print("Enter the world in which the game takes place: ");
		String bg = world.nextLine();
		
		VideoGame VGD = new VideoGame(name, hero, villian, bg);
		System.out.println(VGD);
		
		logo.close();
		savior.close();
		badGuy.close();
		world.close();
	}

}
