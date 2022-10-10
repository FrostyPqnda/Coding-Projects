import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ValidSudoku 
{
    static int[][] board;
    static Scanner scan;

    public static void main(String[] args) throws FileNotFoundException 
    {
        File inFile = new File("valid.txt");
        int N = getSize(inFile);

        board = new int[N][N];
        scan = new Scanner(inFile);

        while(scan.hasNextLine())
        {
            for(int i = 0; i < N; i++) 
            {
                String[] line = scan.nextLine().trim().split(" ");
                for(int j = 0; j < N; j++) 
                {
                    board[i][j] = Integer.parseInt(line[j]);
                }
            }
        }

        printBoard(board);
        scan.close();
    }


    static int getSize(File file) throws FileNotFoundException 
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

    
    static int[][] loadFile(File file) throws FileNotFoundException
    {
        int N = getSize(file);
        int[][] grid = new int[N][N];

        return grid;
    }

    static void printBoard(int[][] board) 
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