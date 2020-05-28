import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class Brownie controls the behavior of the Brownie on the game
 * screen
 * 
 * @author Brian P. 
 * @version 18 March 2020
 */
public class Brownie extends Actor
{
    private GreenfootSound clickPoint = new GreenfootSound("Chomp.wav");
    /**
     * Act - do whatever the Brownie wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(getWorld().isPaused == false)
        {
            addPoint();
            setLocation(getWorld().getWidth() / 2, (getWorld().getHeight() / 2) - 15);
        }
    }
    /**
     * Method bounce adds a bounce effect when the mouse is
     * hovering above the brownie
     */
    private void bounce()
    {
        if(Greenfoot.mouseMoved(this))
        {
            setImage("Big.png");
        }
        if((Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this)))
        {
            setImage("Test.png");
        }
    }
    /**
     * Method addPoint adds a point for each click on the cookie
     */
    private void addPoint()
    {
        if(Greenfoot.mouseClicked(this))
        {
            clickPoint.play();
            Score point = getWorld().getScore();
            point.addPoint(1);
            generateBrownies();
        }
    }
    /**
     * Method generateCookies will generate mini brownies for each click
     */
    public void generateBrownies()
    {
        MiniBrownies mini = new MiniBrownies();
        getWorld().addObject(mini, Greenfoot.getRandomNumber(getWorld().getWidth()), 0);
    }
    /**
     * Method getWorld returns the actor's world as a BrownieWorld
     */
    public BrownieWorld getWorld()
    {
        return (BrownieWorld)(super.getWorld());
    }
}
