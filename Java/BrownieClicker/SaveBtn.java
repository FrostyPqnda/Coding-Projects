import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class SaveBtn contains the save button image that is displayed on screen
 * 
 * @author Brian P. 
 * @version 18 March 2020
 */
public class SaveBtn extends Actor
{
    /**
     * Act - do whatever the SaveBtn wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(getWorld().isPaused == false)
        {
            setLocation(getWorld().getWidth() / 2, 363);
        }
    }
    /**
     * Method getWorld returns the actor's world as a BrownieWorld
     */
    public BrownieWorld getWorld()
    {
        return (BrownieWorld)(super.getWorld());
    }
}
