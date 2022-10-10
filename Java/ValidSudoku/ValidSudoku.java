import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class SudokuFile
{
    int[][] board;
    Scanner scan;

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

public class ValidSudoku 
{
    public static void main(String[] args) throws FileNotFoundException 
    {
        File inFile = new File("valid.txt");
        SudokuFile sf = new SudokuFile(inFile);
        sf.printBoard();

        for(int i = 0; i < sf.getBoard().length; i++) {
            System.out.println(checkRow(sf.getBoard(), i));
        }
    }

    static boolean checkRow(int[][] board, int index) {
        boolean validRow = true;
        for(int i = 0; i < board.length; i++)
        {
            for(int j = i + 1; j < board.length; j++) 
            {
                if(board[index][i] == board[index][j])
                {
                    validRow = false;
                }
            }
        }

        return validRow;
    }

    static 
}