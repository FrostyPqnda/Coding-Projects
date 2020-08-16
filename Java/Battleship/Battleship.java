import java.util.*;

public class Battleship 
{
	private static final int MAX_COL = 10;
	private static final char MAX_ROW = 'J';
    
	private static final int HORIZONTAL = 0;
	private static final int VERTICAL = 1;
	
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) 
	{
		System.out.println("=======================\nWelcome to Battle Ship\n=======================");
        
		Player human = new Player();
		Player computer = new Player();
	
		setUpShips(human, computer);
	
		System.out.print("Hit enter to start guessing.");
		String guess = scan.nextLine();
        
		boolean gameOver = false;

		while(!gameOver)
		{
		    gameOver = playRound(human, computer);
		}
        
		if(human.hasWon())
		{
		    System.out.println("You won!");
		}
		else
		{
		    System.out.println("You lost!");
		}

		System.out.println("Thanks for playing!");
	}
	
	private static void setUpShips(Player human, Player computer)
	{
		System.out.println("Time to place your ships.");
		initializeShipsFromInput(human);

		System.out.print("Hit enter for the enemy to choose their ship locations.");
		String placeEnemy = scan.nextLine();

		computer.initializeShipsRandomly();

		System.out.println("The enemy has placed their ships.");
		computer.printMyShips();
	}
    
	public static void initializeShipsFromInput(Player player)
	{
		for(int i = 0; i < player.NUM_SHIPS; i++)
		{
			System.out.print("Hit enter to place the next ship.");
			String place = scan.nextLine();
			System.out.println("Your current grid of ships.");
			player.printMyShips();

			int length = player.SHIP_LENGTHS[i];
			System.out.println("Now you need to place a ship of length " + length);
			int row = readRow();
			int col = readCol();
			int dir = readDirection();

			player.chooseShipLocation(new Ship(length), row, col, dir); 
		}
		System.out.println("Your current grid of ships.");
		player.printMyShips();
	}
    
	private static int readDirection()
	{
		while(true)
		{
			System.out.print("Horizontal or vertical? ");
			String dir = scan.nextLine();
			dir = dir.toUpperCase();
            
			if(dir.length() > 0)
			{
				if(dir.charAt(0) == 'H')
				{
				    return HORIZONTAL;
				}
				else if(dir.charAt(0) == 'V')
				{
				    return VERTICAL;
				}
			}
			System.out.println("Invalid direction, please try again.");
		}
	}
    
	private static int readCol()
	{
		while(true)
		{
			System.out.print("Which column? (1-" + MAX_COL + ") ");
			int col = scan.nextInt();
			if(col >= 1 && col <= MAX_COL)
			{
				return col-1;
			}
			System.out.println("Invalid column, please try again.");
		}
	}
    
	private static int readRow()
	{
		while(true)
		{
			System.out.print("Which row? (A-" + MAX_ROW + ") ");
			String row = scan.nextLine();
			row = row.toUpperCase();
			if(row.length() > 0)
			{
				char ch = row.charAt(0);
				if(ch >= 'A' && ch <= MAX_ROW)
				{
					return ch - 'A';
				}
			}
			System.out.println("Invalid row, please try again.");
		}
	}
    
	// Plays a round of battle ship, returns true if the game
	// is over, false otherwise.
	private static boolean playRound(Player human, Player computer)
	{
		System.out.print("Hit enter for your turn.");
		String playerNext = scan.nextLine();
		humanTurn(human, computer);
		
		if(human.hasWon())
		{
			return true;
		}
	
		System.out.print("Hit enter for the computer turn.");
		String computerNext = scan.nextLine();
		computerTurn(human, computer);

		return computer.hasWon();
	}

	private static void computerTurn(Player human, Player computer)
	{
		int row = computer.getRandomRowGuess();
		int col = computer.getRandomColGuess();
		System.out.println("Computer player guesses row " + (row + 1) + " and column " + (col + 1));

		boolean hit = computer.makeGuess(row, col, human);
		
		if(hit)
		{
			System.out.println("Computer hit!");
		}
		else
		{
			System.out.println("Computer missed.");
		}

		computer.printMyGuesses();
		computer.printHitsDelivered();
	}

	private static void humanTurn(Player human, Player computer)
	{
		System.out.println("Enemy grid");
		human.printMyGuesses();
		System.out.println("It's your turn to guess.");
		int row = readRow();
		int col = readCol();
		
		boolean hit = human.makeGuess(row, col, computer);

		if(hit)
		{
			System.out.println("You got a hit!");
		}
		else
		{
			System.out.println("Nope, that was a miss.");
		}

		human.printMyGuesses();
		human.printHitsDelivered();
	}

}
