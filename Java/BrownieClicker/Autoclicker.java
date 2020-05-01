import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class Autoclicker controls the upgrade functions of the ClickBtn class
 * 
 * @author Brian P.
 * @version 18 March 2020
 */
public class Autoclicker extends ClickBtn
{
    private int timer = 50;
    /**
     * Act - do whatever the Autoclicker wants to do. This method is called whenever
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
            timer = 50;
        }
    }
    /**
     * Method getWorld returns the actor's world as a CookieWorld
     */
    public BrownieWorld getWorld()
    {
        return (BrownieWorld)(super.getWorld());
    }
}
