import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class GameOver contains the game over image and sets the location of that image
 * 
 * @author Brian P.
 * @version 9 December 2019
 */
public class GameOver extends Actor
{
    /**
     * Act - do whatever the GameOber wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        setLocation(getWorld().getWidth() / 2, getWorld().getHeight() / 2);
    }
    /**
     * Method getWorld returns the actor's world as FlappyWorld
     */
    public FlappyWorld getWorld()
    {
        return (FlappyWorld)(super.getWorld());
    }
}
