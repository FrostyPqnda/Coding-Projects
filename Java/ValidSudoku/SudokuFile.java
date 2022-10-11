import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SudokuFile
{
    int[][] board;
    static Scanner scan;

    public SudokuFile(File file) throws FileNotFoundException 
    {
        board = loadFile(file);
        scan.close();
    }

    int getSize(File file) throws FileNotFoundException 
    {
        scan = new Scanner(file);
        int N = 0;

        while(scan.hasNextLine()) 
        {
            N++;
            scan.nextLine();
        }
        
        scan.close();
        return N;
    }

    int[][] loadFile(File file) throws FileNotFoundException
    {
        int N = getSize(file);
        int[][] grid = new int[N][N];

        scan = new Scanner(file);

        while(scan.hasNextLine())
        {
            for(int i = 0; i < N; i++) 
            {
                String[] line = scan.nextLine().trim().split(" ");
                for(int j = 0; j < N; j++) 
                {
                    grid[i][j] = Integer.parseInt(line[j]);
                }
            }
        }

        scan.close();
        return grid;
    }

    public int[][] getBoard() 
    {
        return board;
    }

    public void printBoard() 
    {
        for(int i = 0; i < board.length; i++) 
        {
            for(int j = 0; j < board[i].length; j++)
            {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}