import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class FactoryBtn controls the timer and score counter of the Factory
 * upgrade
 * 
 * @author Brian P.
 * @version 18 March 2020
 */
public class FactoryBtn extends Button
{
    private int factoryPrice = 150; // Starting price for factory upgrade
    private int TN = 0; // Used to calculate future price
    /**
     * Act - do whatever the FactoryBtn wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(getWorld().isPaused == false)
        {
            setLocation(50, 150);
            checkBuy();
            getWorld().showText("" + factoryPrice, 105, 150);
        }
    }    
    /**
     * Method checkBuy subtracts from points after purchase
     */
    public void checkBuy()
    {
        Score factoryCost = getWorld().getScore();
        if(factoryCost.getPoint() >= factoryPrice)
        {
            if(Greenfoot.mouseClicked(this))
            {
                buyFactory();
                Greenfoot.playSound("Upgrade.wav");
                factoryCost.subtractPoint(factoryPrice);
                TN++;
                updateFactoryCounter();
                futureValue();
                getWorld().antiCheatScore();
            }
        }
    }
    /**
     * Method futureValue will change value after purchase
     */
    public void futureValue()
    {
        factoryPrice += ((5^(TN+1))*(TN+1) + (1+(3^TN)*(TN)));
    }
    /**
     * Method updateFactoryCounter keeps track of upgrades bought
     */
    private void updateFactoryCounter()
    {
        Score point = getWorld().getFactoryCounter();
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
