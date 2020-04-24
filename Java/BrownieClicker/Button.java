import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class Button holds all of the upgrade button icons and controls the
 * placement of said icons
 * 
 * @author Brian P. 
 * @version 18 March 2020
 */
public class Button extends Actor
{
    public void buyGranny()
    {
        Grandma granny = new Grandma();
        int x = Greenfoot.getRandomNumber(35)+54;
        int y = Greenfoot.getRandomNumber(14)+94;
        getWorld().addObject(granny, x, y);
    }
    public void buyFactory()
    {
        Factory factorial = new Factory();
        int x = Greenfoot.getRandomNumber(35)+54;
        int y = Greenfoot.getRandomNumber(14)+190;
        getWorld().addObject(factorial, x, y);
    }
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
