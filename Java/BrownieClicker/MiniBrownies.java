import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class MiniBrownies controls the behavior of the mini brownies that are
 * being generated when the player clicks on the large brownie
 * 
 * @author Brian P.
 * @version 18 March 2020
 */
public class MiniBrownies extends Actor
{
    /**
     * Act - do whatever the MiniBrownies wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        move(4);
        if(getY() >= getWorld().getHeight() - 2)
        {
            getWorld().removeObject(this);
        }
    }
    /**
     * Constructor that controls the image and rotation of the mini brownies
     */
    public MiniBrownies()
    {
        GreenfootImage mini = getImage();  
        mini.scale(mini.getWidth() - 525, mini.getHeight() - 525);  
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
