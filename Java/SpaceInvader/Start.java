import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class Start holds the start button image and controls the size of the start
 * button
 * 
 * @author Brian P.
 * @version 9 February 2020
 */
public class Start extends Actor
{
    public Start()
    {
        GreenfootImage btn = getImage();
        btn.scale(btn.getWidth() + 250, btn.getHeight() + 125);
        setImage(btn);
    }
}
