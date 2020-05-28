import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class Score controls the player score
 * 
 * @author Brian P.
 * @version 18 March 2020
 */
public class Score extends Actor
{
   private int target = 0;
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
   public void setPlayerScore(int score)
   {
       GreenfootImage scoreImage = getImage();
       scoreImage.clear();
       
       Font font = new Font("Poetsen One", 28);
       scoreImage.setFont(font);
       Color color = new Color(238, 238, 238);
       scoreImage.setColor(color);
       
       scoreImage.drawString("" + score, 144, 35);
       target = score;
       setImage(scoreImage);
   }
   /**
    * Act - do whatever the Score wants to do. This method is called whenever
    * the 'Act' or 'Run' button gets pressed in the environment.
    */
   public void act() 
   {
       setPlayerScore(target);
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
   /**
    * Method getWorld returns the actor's world as a BrownieWorld
    */
   public BrownieWorld getWorld()
   {
       return (BrownieWorld)(super.getWorld());
   }
}
