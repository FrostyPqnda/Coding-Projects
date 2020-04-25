import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Class Score contains the score and lives
 * object
 * 
 * @author Brian 
 * @version 9 February 2020
 */
public class Score extends Actor
{
   /**
    * Constructor for the Score object
    */
   public Score()
   {
       GreenfootImage scoreDisplay = new GreenfootImage(250,36);
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
    
       Font scoreFont = new Font("Retro Gaming", 20);
       scoreImage.setFont(scoreFont);
    
       scoreImage.setColor(Color.WHITE);
       scoreImage.drawString("SCORE: " + score, 100, 35);
       setImage(scoreImage);
    }
   /**
    * Method setLives creates the
    * lives object
    */
    public void setLives(int life)
   {
       GreenfootImage lives = getImage();
       lives.clear();
       
       Font livesFont = new Font("Retro Gaming", 20);
       lives.setFont(livesFont);
       
       lives.setColor(Color.WHITE);
       lives.drawString("LIVES: " + life, 118, 35);
       setImage(lives);
   }
   /**
    * Method getWorld() returns the actor's world as an InvasionWorld
    */
   public InvasionWorld getWorld()
   {
        return (InvasionWorld)(super.getWorld());
   }
}
