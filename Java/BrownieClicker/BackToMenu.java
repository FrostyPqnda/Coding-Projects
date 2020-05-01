import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class BackToMenu - back to menu icon that appears when the game starts
 * 
 * @author Brian P.
 * @version 1 May 2020
 */
public class BackToMenu extends Actor
{
    public BackToMenu()
    {
        GreenfootImage backMain = getImage();
        backMain.scale(backMain.getWidth() / 2, backMain.getHeight() / 2);
        setImage(backMain);
    }
}
