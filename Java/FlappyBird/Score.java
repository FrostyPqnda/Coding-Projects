import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Class Score controls the behavior of the player's score counter displayed on the screen
 * 
 * @author Brian P.
 * @version 9 December 2019
 */
public class Score extends Actor
{
    /**
     * Constructor for the Score class
     */
    public Score()
    {
        GreenfootImage scoreDisplay = new GreenfootImage(100, 60);
        setImage(scoreDisplay);
    }
    /**
     * setScore method that creates the score counter to be displayed on screen.
     */
    public void setScore(int score)
    {
        GreenfootImage scoreImage = getImage();
        scoreImage.clear();
        
        Font scoreFont = new Font("Poetsen One", 35);
        scoreImage.setFont(scoreFont);
        
        scoreImage.setColor(Color.BLACK);
        scoreImage.drawString("" + score, 38, 35);
        setImage(scoreImage);
    }
}