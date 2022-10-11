/**
 * The ValidSudoku program reads in a completed sudoku
 * from a text file and checks whether or not the sudoku
 * is a valid solution.
 * 
 * Description of Sudoku: A logic-based game in which players 
 * are to fill an NxN grid (N is a perfect square) with values
 * from [1, N] in a manner such that all rows, column, and grid
 * do not contain duplicate values
 * 
 * Changelog:
 * - October 10, 2022:
 *   - Started the ValidSudoku program
 *   - Created 3 checking conditions for the Sudoku board
 *     to see if the given solution is valid
 *     - checkRow(board, int rowNum)
 *     - checkColumn(board, int columnNum)
 *     - checkBox(board, int boxNum)
 *   - Created a helper function that extracts a submatrix
 *     from the original board.
 * 
 * To-Do List:
 * - Make the program work even for an incomplete Sudoku file
 * - Code compression 
 * 
 * @author Brian Pham
 * @version 1.0
 * @since 2022-10-10;
 */

import java.util.*; 
import java.io.*;

public class ValidSudoku 
{
    public static void main(String[] args) throws FileNotFoundException 
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a Sudoku file: ");
        String input = scan.nextLine();
        File inFile = new File(input);
        SudokuFile sf = new SudokuFile(inFile);
        sf.printBoard();
        System.out.println("Valid Sudoku? " + checkSudoku(sf.getBoard()));
        scan.close();
    }

    /**
     * Checks if the given Sudoku board is a valid solution.
     * @param board
     * @return If the board is a valid Sudoku solution
     */
    static boolean checkSudoku(int[][] board) 
    {
        boolean validBoard = true;
        
        for(int i = 1; i <= 9; i++)
        {
            if(!checkRow(board, i)) 
            {
                validBoard = false;
                break;
            }

            if(!checkColumn(board, i)) 
            {
                validBoard = false;
                break;
            }

            if(!checkBox(board, i)) 
            {
                validBoard = false;
                break;
            }
        }

        return validBoard;
    }

    /**
     * Checks if a given row of a Sudoku board is valid (has no duplicate values).
     * @param board The Sudoku board
     * @param row Row to be checked
     * @return If the given row of a Sudoku board is valid
     */
    static boolean checkRow(int[][] board, int row) {
        int[] rowArr = new int[board.length];
        for(int i = 0; i < rowArr.length; i++)
        {
            rowArr[i] = board[row - 1][i];
        }
        
        boolean validRow = true;
        for(int i = 0; i < rowArr.length; i++)
        {
            for(int j = i + 1; j < rowArr.length; j++)
            {
                if(rowArr[i] == rowArr[j])
                {
                    validRow = false;
                    break;
                }
            }
        }
        return validRow;
    }

    /**
     * Checks if a given column of a Sudoku board is valid (has no duplicate values).
     * @param board The Sudoku board
     * @param column Column to be checked
     * @return If the given column of a Sudoku board is valid
     */
    static boolean checkColumn(int[][] board, int column)
    {
        int[] colArr = new int[board.length];
        for(int i = 0; i < colArr.length; i++)
        {
            colArr[i] = board[i][column - 1];
        }

        boolean validColumn = true;
        for(int i = 0; i < colArr.length; i++)
        {
            for(int j = i + 1; j < colArr.length; j++)
            {
                if(colArr[i] == colArr[j])
                {
                    validColumn = false;
                    break;
                }
            }
        }
        return validColumn;
    }
    
    /**
     * Sets the subgrid range of a given Sudoku board
     * @param startRow Starting row position
     * @param endRow Ending row position (not included)
     * @param startCol Starting column position
     * @param endCol Ending column position (not included)
     */
    static void setBoxRange(List<int[]> list, int startRow, int endRow, int startCol, int endCol)
    {
        int[] rowRange = {-1, -1};
        int[] colRange = {-1, -1};
        rowRange[0] = startRow;
        rowRange[1] = endRow;
        list.add(rowRange);
        colRange[0] = startCol;
        colRange[1] = endCol;
        list.add(colRange);
    }

    /**
     * Extracts a starting and ending position for the row and column
     * to be used for the subgrid of Sudoku board based on the box 
     * number i.e. Box #7 will have a row from [6, 9) and a columm
     * from [0, 3).
     * @param box A box that we want to set a row and column range for
     */
    static List<int[]> getBoxRange(int box)
    {   
        List<int[]> list = new ArrayList<int[]>();
        switch(box)
        {
            case 1:
                setBoxRange(list, 0, 3, 0, 3);
                break;
            case 2:
                setBoxRange(list, 0, 3, 3, 6);
                break;
            case 3:
                setBoxRange(list, 0, 3, 6, 9);
                break;
            case 4:
                setBoxRange(list, 3, 6, 0, 3);
                break;
            case 5:
                setBoxRange(list, 3, 6, 3, 6);
                break;
            case 6:
                setBoxRange(list, 3, 6, 6, 9);
                break;
            case 7:
                setBoxRange(list, 6, 9, 0, 3);
                break;
            case 8:
                setBoxRange(list, 6, 9, 3, 6);
                break;
            case 9:
                setBoxRange(list, 6, 9, 6, 9);
                break;
            default:
                break;
        }

        return list;
    }

    /**
     * Extracts a submatrix from the original matrix
     * @param board Board to extract a submatrix from
     * @param box A box that we want to extract out
     * @return a submatrix of a Sudoku board given a box number from [1, 9]
     */ 
    static int[][] getBox(int[][] board, int box)
    {
        List<int[]> list = getBoxRange(box);
        int startRow = list.get(0)[0], endRow = list.get(0)[1];
        int startCol = list.get(1)[0], endCol = list.get(1)[1];
        int[][] grid = new int[3][3];

        for(int i = startRow; i < endRow; i++)
        {
            for(int j = startCol; j < endCol; j++)
            {
                int r = i - startRow, c = j - startCol;
                grid[r][c] = board[i][j];
            }
        }

        return grid;
    }

    /**
     * Checks if a given submatrix of a Sudoku board is valid (has no duplicate values).
     * @param board The sudoku board
     * @param box Box (submatrix) to be checked
     * @return If the given box of a Sudoku board is valid
     */
    static boolean checkBox(int[][] board, int box)
    {
        int[][] grid = getBox(board, box);
        int[] grid1D = new int[board.length];
        int index = 0;

        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid.length; j++)
            {
                grid1D[index++] = grid[i][j];
            } 
        }
        
        boolean validBox = true;
        for(int i = 0; i < grid1D.length; i++)
        {
            for(int j = i + 1; j < grid1D.length; j++)
            {
                if(grid1D[i] == grid1D[j])
                {
                    validBox = false;
                    break;
                }
            }
        }
        return validBox;
    }
}