import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class Factory controls the upgrade functions of the FactoryBtn class
 * 
 * @author Brian P. 
 * @version 18 March 2020
 */
public class Factory extends Actor
{
    private int timer = 100;
    /**
     * Act - do whatever the Factory wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        bakeBrownies();
    }  
    /**
     * Method bakeCookies bakes a batch of cookies
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
            timer = 100;
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
