import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class Container sets the size of the upgrade container boxes that all 
 * upgrade icons are placed in
 * 
 * @author Brian P.
 * @version 18 March 2020
 */
public class Container extends Button
{
    public Container()
    {
        GreenfootImage box = getImage();
        box.scale(box.getWidth() + 25, box.getHeight() + 3);
        setImage(box);
    }
}

