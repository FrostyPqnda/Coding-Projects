import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
* Class FlappyBird controls the behavior of the FlappyBird player
* 
* @author Brian P.
* @version 9 December 2019
*/
public class FlappyBird extends Actor
{
    // Constant variables for Flappy Bird
    double dy = 0;
    double g = 1;
    double BOOST_SPEED = -6.0;
    GreenfootImage upWing;
    GreenfootImage straightWing;
    GreenfootImage downWing;
    int animFrame = 1;
    int ANIM_RATE = 5;
    int end = 0;
    boolean dead = false;
    // Audio
    GreenfootSound hit;
    GreenfootSound wing;
    GreenfootSound die;
    
    /**
     * The FlappyBird method that contains the animation images and sounds for
     * Flappy Bird.
     */
    public FlappyBird()
    {
        upWing = new GreenfootImage("flappybird1.png");
        straightWing = new GreenfootImage("flappybird2.png");
        downWing = new GreenfootImage("flappybird3.png");
        hit = new GreenfootSound("Hit.wav");
        wing = new GreenfootSound("Wing.wav");
        die = new GreenfootSound("Die.wav");
    }
    /**
     * Act - do whatever the FlappyBird wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {   
        if (getWorld().startPressed == true)
        {
            if(dead == false)
            {
                if(getWorld().isPaused == false)
                {
                    flappyFunctions();
                } 
            }
            else
            {
                death();
            }
        }
    }
    /**
     * Method displayGameOver that displays the 
     * Game Over image once Flappy dies.
     */
    private void displayGameOver()
    {
        int gameOverXPos = getWorld().getWidth() / 2;
        int gameOverYPos = getWorld().getHeight() / 2;
        dead = true;
        GameOver gameOver = new GameOver();
        getWorld().addObject(gameOver, gameOverXPos, gameOverYPos);
    }
    /**
     * Method flappyRotation that changes Flappy's rotation
     * depending on its rotation.
     */
    private void flappyRotation()
    {
        if (-10 <= dy && dy <= 0)
        {
            setRotation(330);
        }
        else if(dy > 10)
        {
            setRotation(30);
        }
        else
        {
            setRotation(0);
        }
    }
    /**
     * Method animatedWingFrames changes Flappy's
     * animation based on the frames.
     */
    private void animatedWingFrames()
    {
        if (animFrame == ANIM_RATE)
        {
            setImage(upWing);
        }
        else
        {   
            if (animFrame == (ANIM_RATE * 2))
            {
                setImage(straightWing);
            }
            else
            {
                if (animFrame == (ANIM_RATE * 3))
                {
                    setImage(downWing);
                }
                else
                {
                    if(animFrame == (ANIM_RATE * 4))
                    {
                        animFrame = 0;
                    }
                }
            }
        }
        animFrame++;
    }
    /**
     * Method flappyFunctions that sets how Flappy should work 
     * (the rules for Flappy),
     */
    private void flappyFunctions()
    {
        flappyRotation();
        if((getOneIntersectingObject(Pipe.class) != null) || ((getOneIntersectingObject(Ground.class) != null)) || ((getOneIntersectingObject(bottomPipe.class) != null)) ||  getY() < 0 || getY() > getWorld().getHeight()) 
        {
            hit.play();
            die.play();
            displayGameOver();
        }
        setLocation(getX(), (getY() + (int)dy));
        if(Greenfoot.isKeyDown("space"))
        {
            dy = BOOST_SPEED;
            wing.play();
        }
        if(isAtEdge())
        {
            displayGameOver();
        }
        dy = dy + g;
        animatedWingFrames();
    }
    /**
     * Method death controls the death animation of FlappyBird when it dies
     */
    private void death()
    {
        getWorld().isPaused = true;
        setRotation(0);
        if (end < 30)
        {
            setLocation(getX(), (int)(getY() - 1));
            end++;
        }
        else if (end < 50)
        {
            setLocation(getX(), getY());
            end++;
        }
        else if(isAtEdge())
        {
            setRotation(45);
            dy = dy + g;
            setLocation(getX(), (int)(getY() + dy));
        }
        else
        {
            setRotation(90);
            setLocation(getX(), getY() + 100);
            getWorld().removeObject(this);
            Greenfoot.stop();
        }
    }
    /**
     * Method getWorld returns the actor's world as FlappyWorld
     */
    public FlappyWorld getWorld()
    {
        return (FlappyWorld)(super.getWorld());
    }
}
