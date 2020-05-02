import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class Button stores all of the upgrade methods that allows for each 
 * upgrades to call upon them when they have been bought
 * 
 * @author Brian P. 
 * @version 18 March 2020
 */
public class Button extends Actor
{
    /**
     * Method buyGranny adds a grandma upgrade icon when 
     * a grandma upgrade is purchased
     */
    public void buyGranny()
    {
        Grandma granny = new Grandma();
        int x = Greenfoot.getRandomNumber(35)+54;
        int y = Greenfoot.getRandomNumber(14)+94;
        getWorld().addObject(granny, x, y);
    }
    /**
     * Method buyGranny adds a factory upgrade icon when 
     * a factory upgrade is purchased
     */
    public void buyFactory()
    {
        Factory factorial = new Factory();
        int x = Greenfoot.getRandomNumber(35)+54;
        int y = Greenfoot.getRandomNumber(14)+190;
        getWorld().addObject(factorial, x, y);
    }
    /**
     * Method buyClicker adds a autoclick upgrade icon when
     * an autoclick upgrade is purchased
     */
    public void buyClicker()
    {
        Autoclicker auto = new Autoclicker();
        int x = Greenfoot.getRandomNumber(35)+54;
        int y = Greenfoot.getRandomNumber(14)+290;
        getWorld().addObject(auto, x, y);
    }
    /**
     * Method getWorld returns the actor's world as a BrownieWorld
     */
    public BrownieWorld getWorld()
    {
        return (BrownieWorld)(super.getWorld());
    } 
}
