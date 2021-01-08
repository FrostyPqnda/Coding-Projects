public class TicTacToe
{
    private int turn;
    private String[][] board; 
    
    /**
     * TicTacToe class constructor
     * 
     * Sets up the game board
     */
    public TicTacToe() 
    {
        turn = 1;
        board = new String[3][3];
        for(int row = 0; row < 3; row++) 
            for(int col = 0; col < 3; col++)
                board[row][col] = "-";
    }
    
    // getTurn() returns the turn variable
    public int getTurn()
    {
       return turn;
    }
    
    // checkTurn() checks if the # turns is < 10
    public boolean checkTurn()
    {
        return turn < 10;
    }
    
    // printBoard() prints the game board to console
    public void printBoard()
    {
        System.out.println("  0 1 2");
        for(int row = 0; row < 3; row++)
        {
            System.out.print(row + " ");
            for(int col = 0; col < 3; col++)
                System.out.print(board[row][col] + " ");
            System.out.println();
       }
    }
    
    // pickLocation() checks if space [row, col] is a valid space
    public boolean pickLocation(int row, int col)
    {
        if((row >= 0 && row < 3) && (col >= 0 && col < 3) && board[row][col].equals("-"))
           return (!(board[row][col].equals("X") && board[row][col].equals("O")));
        return false;
    }
    
    // takeTurn() places an X or O at location [row, col] based on the int turn
    public void takeTurn(int row, int col)
    {
        if(getTurn() % 2 == 0)
            board[row][col] = "O";
        else
            board[row][col] = "X"; 
        turn++;
    }
    
    // checkRow() checks if there is any row of three X or O's
    public boolean checkRow()
    {
        for(int i = 0; i < board.length;)
            return board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]) && !board[i][0].equals("-");
        return false;
    }
  
    // checkCol() checks if there is any column of three X or O's
    public boolean checkCol()
    {
        for(int i = 0; i < board.length;)
            return board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]) && !board[0][i].equals("-");
        return false;
    }
  
    // checkDiag() checks if there is any diagonal of three X or O's
    public boolean checkDiag()
    {
        boolean diagOne = board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && !board[0][0].equals("-");
        boolean diagTwo = board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && !board[0][2].equals("-");
        return diagOne || diagTwo;
    }
    
    // checkTie() checks if the game is a tie (checkWin() is false and turn is > 10)
    public boolean checkTie()
    {
        return !checkWin() && !checkTurn();
    }
  
    // checkWin() returns true if either of the 3 check method is true
    public boolean checkWin()
    {
        return checkRow() || checkCol() || checkDiag();
    }
}