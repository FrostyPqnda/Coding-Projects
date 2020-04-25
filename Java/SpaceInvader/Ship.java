import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class Ship controls the behavior of the player's ship
 * 
 * @author Brian P.
 * @version 9 February 2020
 */
public class Ship extends Actor
{
    private int speed; // Variable that controls how fast the player is moving left or right
    private int counter; // Variable that counts how long until the player can shoot again
    private int RELOAD_TIME = 25; // Constant that sets the counter each time the player fires
    /**
     * Class constructor for the Ship class.
     */
    public Ship()
    {
        speed = 5;
        counter = 0;
    }
    /**
     * Act - do whatever the Ship wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(getWorld().isPaused == false)
        {
            if(counter > 0)
            {
                counter--;
            }
            checkKeys();
        }
    }
    /**
     * Method checkKeys allows the player's ship
     * to move left or right.
     */
    private void checkKeys()
    {
        if(Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left"))
        {
            if(getX() >= getImage().getWidth())
            {
                setLocation(getX() - speed, getY());
            }
        }
        else if(Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right"))
        {
            if(getX() <= getWorld().getWidth() - getImage().getWidth())
            {
                setLocation(getX() + speed, getY());
            }
        }
        if(Greenfoot.isKeyDown("space"))
        {
            if(counter == 0)
            {
                ShipBullet sb = new ShipBullet();
                getWorld().addObject(sb, getX(), getY() - 5);
                Greenfoot.playSound("shoot.wav");
                counter = RELOAD_TIME;
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
