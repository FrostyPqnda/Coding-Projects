    import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
    
    /**
     * Class bottomPipe controls the speed and behavior of the bottom pipes
     * 
     * @author Brian P.
     * @version 9 December 2019
     */
    public class bottomPipe extends Actor
    {
        // Int variable that helps change the pipe's x-position
        int PIPE_SPEED = -4;
        /**
         * method that gets the image for the top pipe and sets the top pipes x and y position.
         * Also removes the pipe from the world after Flappy has gone pass the pipe and the pipe
         * is out of world.
         */
        public void act() 
        {
            if (getWorld().isPaused == false)
            {
                GreenfootImage image = getImage();
                setLocation(getX() + PIPE_SPEED, getY());
                if(getX() < 0)
                {
                    getWorld().removeObject(this);
                }
            }
        }
        /**
         * Method getWorld returns the actor's world as FlappyWorld
         */
        public FlappyWorld getWorld()
        {
            return (FlappyWorld)(super.getWorld());
        }
    }
