import java.util.Scanner;

public class TicTacToeTester
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        TicTacToe tic = new TicTacToe();
        
        tic.printBoard();
        int row, col;
        
        while(true) {
            System.out.println("\nEnter the row and column you want to place [0 - 2]:");
            System.out.print("Row: ");
            row = scan.nextInt();
            System.out.print("Column: ");
            col = scan.nextInt();
            
            if(tic.pickLocation(row, col))
                tic.takeTurn(row, col);
            else 
                System.out.println("Sorry, that position is occupied. Please select a different position.");
            
            System.out.println();
            
            tic.printBoard();
            
            if(tic.checkWin())
            {
                System.out.println("\nWINNER!");
                break;
            }
                
                
            if(tic.checkTie())
            {
                System.out.println("\nTIE!");
                break;
            }
        }

        scan.close();
    }
}
