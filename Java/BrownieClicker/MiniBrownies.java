import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class MiniBrownies controls the behavior of the mini brownies that are
 * being generated when the player clicks on the large brownie
 * 
 * @author Brian P.
 * @version 18 March 2020
 */
public class MiniBrownies extends Brownie
{
    private boolean toggleMiniBrownies = false; // Display mini brownies?
    /**
     * Act - do whatever the MiniBrownies wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        move(5);
        if(getY() >= getWorld().getHeight() - 2)
        {
            getWorld().removeObject(this);
        }
    }
    /**
     * Constructor for MiniBrownies
     */
    public MiniBrownies()
    {
        GreenfootImage mini = getImage();  
        mini.scale(mini.getWidth() - 600, mini.getHeight() - 600);  
        mini.setTransparency(210);
        setImage(mini); 
        turn(90);
    }
    /**
     * Method getWorld returns the actor's world as a BrownieWorld
     */
    public BrownieWorld getWorld()
    {
        return (BrownieWorld)(super.getWorld());
    }
}
