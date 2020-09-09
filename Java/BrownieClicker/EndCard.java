import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class EndCard displays an end card world after the player wins the game
 * 
 * @author Brian P.
 * @version 18 March 2020
 */
public class EndCard extends World
{
    /**
     * Constructor for objects of class EndCard.
     * 
     */
    public EndCard()
    {    
        // Create a new world with 700x500 cells with a cell size of 1x1 pixels.
        super(750, 550, 1); 
    }
    /**
     * Sets the game to a new BrownieWorld if 'N'
     * is pressed
     */
    public void act()
    {
        if(Greenfoot.isKeyDown("N"))
        {
            Greenfoot.setWorld(new BrownieWorld());
        }
    }
}
