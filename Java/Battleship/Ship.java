public class Ship
{
    public static final int UNSET = -1;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    
    private int row = UNSET;
    private int col = UNSET;
    private int length = UNSET;
    private int direction = UNSET;
    
    public Ship(int length)
    {
        this.length = length;
        
    }
    // Has the location been initialized
    public boolean isLocationSet()
    {
        return row != UNSET && col != UNSET;
    }
    
    // Has the direction been initialized
    public boolean isDirectionSet()
    {
        return direction != UNSET;
    }
    
    // Set the location of the ship 
    public void setLocation(int row, int col)
    {
        this.row = row;
        this.col = col;
    }
    
    // Set the direction of the ship
    public void setDirection(int direction)
    {
        this.direction = direction;
    }
    
    // Getter for the row value
    public int getRow()
    {
        return row;
    }
    
    // Getter for the column value
    public int getCol()
    {
        return col;
    }
    
    // Getter for the length of the ship
    public int getLength()
    {
        return length;
    }
    
    // Getter for the direction
    public int getDirection()
    {
        return direction;
    }
    
    // Helper method to get a string value from the direction
    private String directionToString()
    {
        if(direction == UNSET)
        {
            return "Unset direction.";
        }
        else if(direction == VERTICAL)
        {
            return "VERTICAL.";
        }
        else
        {
            return "HORIZONTAL.";
        }
    }
    // Helper method to get a string value of row and col from location
    private String locationToString()
    {
        if(row == UNSET || col == UNSET)
        {
            return "(Unset location.)";
            
        }
        else
        {
            return "(" + row + ", " + col + ")";
        }
    }
    // toString value for this Ship
    public String toString()
    {
        return directionToString() + " ship of length " + length + " at " + locationToString();
    }
}
