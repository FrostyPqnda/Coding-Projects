/**
 * @author Brian Pham | FrostyPqnda
 * @version 1.0
 * 
 * WordBlock class.
 * 
 * Given a String, row value, and column value, 
 * create a character block (2D Array) of size
 * row value * column value [row value][column value]
 * and fill it up with each character in the String.
 * 
 * Each '-' at the end of the block denotes the existence
 * of a whitespace within the original String, or to
 * compensate the block if its size is greater than
 * the length of the String.
 */
public class WordBlock
{
    // Private instance variable for the WordBlock class
    private char[][] charBlock;

    /**
     * Constructor for the WordBlock class.
     * 
     * Creates a character block with each element
     * filled with a character in the given String.
     * 
     * @param word -> String that will be used to fill up the character block
     * @param row -> Row size of the character block
     * @param col -> Column size of the character block
     */
    public WordBlock(String word, int row, int col) 
    {
        this.charBlock = new char[row][col];
        int index = 0;
        
        for(int r = 0; r < row; r++) 
        {
            for(int c = 0; c < col; c++)
            {
                String newText = word.replace(" ", "");
                boolean inBound = index >= 0 && index < newText.length();
                
                if(inBound) {
                    charBlock[r][c] = newText.charAt(index);
                } else {
                    charBlock[r][c] = '-';
                }

                index++;
            }
        }
    }

    /**
     * @return the character block (2D array)
     */
    public char[][] getWordBlock() 
    {
        return charBlock;
    }

    /**
     * @return the character block as an object String
     */
    public String toString() 
    {
        String blockStr = "";

        for(int r = 0; r < charBlock.length; r++)
        {
            for(int c = 0; c < charBlock[r].length; c++)
            {
                blockStr += charBlock[r][c] + " ";
            }
            blockStr += System.lineSeparator();
        }

        return blockStr;
    }
}