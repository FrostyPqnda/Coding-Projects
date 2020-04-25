import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class UFO controls the behavior of the UFO's that randomly appear at the top
 * of the screen
 * 
 * @author Brian P.
 * @version 9 February 2020
 */
public class UFO extends Actor
{
    /**
     * Act - do whatever the UFO wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(getWorld().isPaused == false)
        {
            if(getX() >= getWorld().getWidth())
            {
                getWorld().removeObject(this);
            }
            else
            {
                for(int i = 0; i < 5; i++)
                {
                    setLocation(getX() + 1, getY());
                }
            }
        }
    }
    /**
     * Method getWorld() returns the actor's world as an InvasionWorld
     */
    public InvasionWorld getWorld()
    {
        return (InvasionWorld)(super.getWorld());
    }
}
