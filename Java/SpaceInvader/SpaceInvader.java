import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class SpaceInvader controls the behavior for the enemy invaders
 * 
 * @author Brian Pham 
 * @version 9 February 2020
 */
public class SpaceInvader extends Actor
{
    private int type; // Which type of Alien image to use
    private boolean form; // Movement form the invader is in
    private int speed; // Speed for all invaders
    private int layer; // The layer that the invader is in
    private int column; // The column that the invader is in
    private int timer; // The timer that determines movement
    private int level; // The current level of the invader (as compared to the world's level)
    private int reloadTime; // The required time between shots
    private int reloadCounter; // The since the last shot
    private int explosion; // Counter for the invader explosion
    private boolean changeForm; // Changes the form of invader based on a true/false statement
    /**
     * SpaceInvader Constructor
     */
    public SpaceInvader()
    {
        this(1, 1, 1);
    }
    /**
     * Constructor for the SpaceInvader
     * (with param tType, lLayer, and
     * cColumn)
     */
    public SpaceInvader(int tType, int lLayer, int cColumn)
    {
        type = tType;
        layer = lLayer;
        column = cColumn;
        
        form = true;
        speed = 20;
        timer = 1;
        level = 0;
        reloadTime = 50;
        reloadCounter = 0;
        explosion = 0;
        switch(type)
        {
            case 3:
            setImage("Alien 3A.png");
            break;
            
            case 2:
            setImage("Alien 2A.png");
            break;
            
            default:
            setImage("Alien 1A.png");
            
        }
    }
    /**
     * Act - do whatever the SpaceInvader wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(getWorld().isPaused == false)
        {
            timer++;
            if(timer % getWorld().getTimerSpeed() == 0)
            {
                setLocation(getX() + speed, getY());
                dropDown();
                changeForm = !changeForm;
                if(explosion == 0)
                {
                    changeImage();
                }
                else
                {
                    explode();
                }
            }
            checkGameOver();
            if(explosion > 0)
            {
                explosion--;
                if(explosion == 0)
                {
                    getWorld().removeObject(this);
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
     * Method dropDown() moves the invader down after traveling to edge
     */
    private void dropDown()
    {
        if(level < getWorld().getInvaderLevel())
        {
            level++;
            speed *= - 1;
            setLocation(getX(), getY() + getImage().getHeight());
        }
    }
    /**
     * Method checkGameOver
     * calls the world's
     * gameOver method
     */
    private void checkGameOver()
    {
        if(getY() + getImage().getHeight() > getWorld().getHeight())
        {
            getWorld().gameOver(false);
        }
    }
    /**
     * Method fire allows the
     * space invader to shoot
     * an invader bullet if 
     * they have reloaded and
     * are not exploding
     */
    public void fire()
    {
        if(reloadCounter == 0 && explosion == 0)
        {
            InvaderBullet ib = new InvaderBullet();
            getWorld().addObject(ib, getX(), getY() + getImage().getHeight() / 2);
            Greenfoot.playSound("invader_blast.wav");
            reloadCounter = reloadTime;
        }
    }
    /**
     * Method changeImage changes the
     * image of the invaders
     */
    private void changeImage()
    {
        if(changeForm == true)
        {
            switch(type)
            {
                case 3:
                setImage("Alien 3B.png");
                break;
                
                case 2:
                setImage("Alien 2B.png");
                break;
                
                default:
                setImage("Alien 1B.png");
            }
        }
        else
        {
            switch(type)
            {
                case 3:
                setImage("Alien 3A.png");
                break;
                
                case 2:
                setImage("Alien 2A.png");
                break;
                
                default:
                setImage("Alien 1A.png");
            }
        }
    }
    /**
     * Method getSpeed returns the speed
     * of the invader
     */
    public int getSpeed()
    {
        return speed;
    }
    /**
     * Method getColumn returns the
     * column of the invader
     */
    public int getColumn()
    {
        return column;
    }
    /**
     * Method getLayer returns the
     * layer the invader is in
     */
    public int getLayer()
    {
        return layer;
    }
    /**
     * Change image to explosion
     */
    public void explode()
    {
        setImage("explosion.png");
        explosion = 5;
    }
}
