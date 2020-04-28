import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class Grandma controls the upgrade functions of the GrandmaBtn class
 * 
 * @author Brian P.
 * @version 18 March 2020
 */
public class Grandma extends Actor
{
    private int timer = 1000;
    /**
     * Act - do whatever the Grandma wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        bakeBrownies();
    }   
    /**
     * Method bakeCookies bakes a batch of brownies
     */
    public void bakeBrownies()
    {
        if(timer > 0)
        {
            timer--;
        }
        if(timer == 0)
        {
            Score timeCounter = getWorld().getScore();
            timeCounter.addPoint(1);
            timer = 1000;
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
