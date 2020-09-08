import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class UpgradeDisplay creates an upgrade counter for the game
 * 
 * @author Brian P. 
 * @version 9 May 2020
 */
public class UpgradeDisplay extends Actor
{
    private int playerUpgrade = 0;
    /**
     * Constructor for UpgradeDisplay
     */
    public UpgradeDisplay()
    {
        GreenfootImage upgradeDisplay = new GreenfootImage(305, 60);
        setImage(upgradeDisplay); 
    }
    /**
     * Method setUpgrade creates the upgrade score
     * object
     */
    public void setUpgrade(int upgrade)
    {
       GreenfootImage upgradeImage = getImage();
       upgradeImage.clear();
       
       Font upgradeFont = new Font("Poetsen One", 20);
       upgradeImage.setFont(upgradeFont);
       Color color = new Color(238, 238, 238);
       upgradeImage.setColor(color);
       
       upgradeImage.drawString("" + upgrade, 144, 35);
       playerUpgrade = upgrade;
       setImage(upgradeImage);
    }
    /**
     * Method addPoint adds points to the current score
     */
    public void addPoint(int score)
    {
       playerUpgrade += score;
    }
    public int getPlayerUpgrade()
    {
        return playerUpgrade;
    }
    /**
     * Act - do whatever the UpgradeDisplay wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        setUpgrade(playerUpgrade);
    }    
}
