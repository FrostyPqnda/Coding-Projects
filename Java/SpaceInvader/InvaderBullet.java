import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Class InvaderBullet controls the behavior of the invader's bullet
 * 
 * @author Brian P. 
 * @version 9 February 2020
 */
public class InvaderBullet extends Actor
{
    private int speed; // How far the bullet travels each tick
    /**
     * Class constructor for the InvaderBullet class
     */
    public InvaderBullet()
    {
        speed = 8;
    }
    /**
     * Act - do whatever the InvaderBullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(getY() > getWorld().getHeight())
        {
            getWorld().removeObject(this);
        }
        else
        {
            for(int i = 0; i < speed; i++)
            {
                setLocation(getX(), getY() + 1);
                if(checkCollision())
                {
                    break;
                }
            }
        }
    }   
    /**
     * Method checkCollision checks for barriers or invader
     * and removes them if they collide.
     */
    private boolean checkCollision()
    {
        Barrier b  = (Barrier)getOneIntersectingObject(Barrier.class);
        Ship player  = (Ship)getOneIntersectingObject(Ship.class);
        if(b != null)
        {
            getWorld().removeObject(b);
            getWorld().removeObject(this);
            return true;
        }
        if(player != null)
        {
            Greenfoot.playSound("explosion.wav");
            getWorld().removeObject(player);
            getWorld().setLives(getWorld().getLives() - 1);
            int shipX = getWorld().getWidth() / 2; // The x position of the player's ship
            int shipY = getWorld().getHeight() * 9 / 10; // The y position of the player's ship
            getWorld().addObject(player, shipX, shipY);
            if(getWorld().getLives() == 0)
            {
                getWorld().removeObject(player);
                getWorld().gameOver(false);
                getWorld().setLives(0);
                Greenfoot.playSound("GO.wav");
            }
            getWorld().removeObject(this);
            return true;
        }
        return false;
    }
    /**
     * Method getWorld() returns the actor's world as an InvasionWorld
     */
    public InvasionWorld getWorld()
    {
        return (InvasionWorld)(super.getWorld());
    }
}
