import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EndCard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EndCard extends World
{
    /**
     * Constructor for objects of class EndCard.
     * 
     */
    public EndCard()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
    }
    public void act()
    {
        
        if(Greenfoot.isKeyDown("N"))
        {
            Greenfoot.setWorld(new BrownieWorld());
        }
    }
}
