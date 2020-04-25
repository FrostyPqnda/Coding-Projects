import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class Score controls the player score and upgrades
 * 
 * @author Brian P.
 * @version 18 March 2020
 */
public class Score extends Actor
{
   private int target = 0;
   private int playerUpgrade = 0;
   Font font = new Font("Poetsen One", 28);
   Color color = new Color(238, 238, 238);
   /**
    * Constructor for the Score object
    */
   public Score()
   {
       GreenfootImage scoreDisplay = new GreenfootImage(305, 60);
       setImage(scoreDisplay); 
   }
   /**
    * Method setScore creates the
    * score object
    */
   public void setScore(int score)
   {
       GreenfootImage scoreImage = getImage();
       scoreImage.clear();
        
       scoreImage.setFont(font);
        
       scoreImage.setColor(color);
       scoreImage.drawString("" + score, 144, 35);
       target = score;
       setImage(scoreImage);
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
       
       upgradeImage.setColor(color);
       upgradeImage.drawString("" + upgrade, 144, 35);
       playerUpgrade = upgrade;
       setImage(upgradeImage);
   }
   /**
    * Act - do whatever the Score wants to do. This method is called whenever
    * the 'Act' or 'Run' button gets pressed in the environment.
    */
   public void act() 
   {
       setScore(target);
   }
   /**
    * Method addPoint adds points to the current score
    */
   public void addPoint(int score)
   {
       target += score;
   }
   /**
    * Method subtractPoints subtracts points from the current score
    */
   public void subtractPoint(int score)
   {
       target -= score;
   }
   /**
    * Method getPoint returns the point
    */
   public int getPoint()
   {
       return target;
   }
   public int getPlayerUpgrade()
   {
       return playerUpgrade;
   }
   /**
    * Method getWorld returns the actor's world as a BrownieWorld
    */
   public BrownieWorld getWorld()
   {
       return (BrownieWorld)(super.getWorld());
   }
}
