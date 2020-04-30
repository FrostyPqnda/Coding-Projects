import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class Instruction creates an instruction screen that tells
 * players how to reload their score if they have saved it
 * 
 * @author Brian P. 
 * @version 30 April 2020
 */
public class Instruction extends Actor
{
    /**
     * Act - do whatever the Instruction wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        setLocation(getWorld().getWidth() / 2, getWorld().getHeight() / 2);
    }
    /**
     * Method getWorld returns the actor's world as a BrownieWorld
     */
    public BrownieWorld getWorld()
    {
        return (BrownieWorld)(super.getWorld());
    }    
}
