import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Class ShipBullet controls the behavior for the ship's bullet
 * 
 * @author Brian P.
 * @version 9 February 2020
 */
public class ShipBullet extends Actor
{
    private int speed; // Controls the speed of bullet
    /**
     * Constructor for ShipBullet
     * class
     */
    public ShipBullet()
    {
        speed = 10;
    }
    /**
     * Act - do whatever the ShipBullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(getY() < 0)
        {
            getWorld().removeObject(this);
        }
        else
        {
            for(int i = 0; i < speed; i++)
            {
                setLocation(getX(), getY() - 1);
                if(checkCollision())
                {
                    break;
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
    /**
     * Method checkCollision checks for barriers or invader
     * and removes them if they collide.
     */
    private boolean checkCollision()
    {
        Barrier b  = (Barrier)getOneIntersectingObject(Barrier.class);
        SpaceInvader si  = (SpaceInvader)getOneIntersectingObject(SpaceInvader.class);
        UFO mothership = (UFO)getOneIntersectingObject(UFO.class);
        if(b != null)
        {
            getWorld().removeObject(b);
            getWorld().removeObject(this);
            return true;
        }
        if(si != null)
        {
            si.explode();            
            Greenfoot.playSound("invaderkilled.wav");
            getWorld().setScore(getWorld().getScore() + 50);
            getWorld().removeObject(this);
            return true;
        }
        if(mothership != null)
        {
            getWorld().removeObject(mothership);
            Greenfoot.playSound("smb_fireball.wav");
            getWorld().setScore(getWorld().getScore() + 150);
            getWorld().removeObject(this);
            return true;
        }
        return false;
    }
}
