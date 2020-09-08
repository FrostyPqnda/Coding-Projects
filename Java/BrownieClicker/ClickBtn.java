import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class ClickBtn controls the timer and score counter of the Autoclicker
 * upgrade
 * 
 * @author Brian P.
 * @version 18 March 2020
 */
public class ClickBtn extends Button
{
    private int autoclick = 250; // Starting price for autoclick upgrade
    private int AN = 0; // Used to calculate future price
    /**
     * Act - do whatever the ClickBtn wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(getWorld().startPressed == true)
        {
            setLocation(50, 285);
            checkBuy();
            getWorld().showText("" + autoclick, 105, 285);
        }
    }   
    /**
     * Method bakeCookies bakes a batch of cookies
     */
    public void checkBuy()
    {
        Score auto = getWorld().getScore();
        if(auto.getPoint() >= autoclick)
        {
            if(Greenfoot.mouseClicked(this))
            {
                buyClicker();
                Greenfoot.playSound("Upgrade.wav");
                auto.subtractPoint(autoclick);
                AN++;
                updateAutoclickCounter();
                futureValue();
                getWorld().saveCurrentData();
            }
        }
    }
    /**
     * Method futureValue will change value after purchase
     */
    public void futureValue()
    {
        autoclick += ((7^(AN+1))*(AN+1) + (1+(7^AN)*(AN)));
    }
    /**
     * Method updateFactoryCounter keeps track of upgrades bought
     */
    private void updateAutoclickCounter()
    {
        UpgradeDisplay point = getWorld().getAutoclickCounter();
        point.addPoint(1);
    }
    /**
     * Method getWorld returns the actor's world as a BrownieWorld
     */
    public BrownieWorld getWorld()
    {
        return (BrownieWorld)(super.getWorld());
    }
}
