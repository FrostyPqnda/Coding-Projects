import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class FlappyWorlds contains all of the objects of the actor classes and controls the game
 * 
 * @author Brian P. 
 * @version 9 December 2019
 */
public class FlappyWorld extends World
{
    // Constant variables
    int pipeCounter = 0;
    int flappyCounter = 0;
    int PIPE_SPACING = 150;
    int score = 0;
    int FIRST_PIPE = 240;
    Score scoreObj = null;
    boolean isPaused = true;
    boolean startPressed = false;
    Start startButton = null;
    Title title = null;
    getReady press = null;
    
    GreenfootSound point;
    /**
     * Constructor for objects of class FlappyWorld.
     * 
     */
    public FlappyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1, false); 
        Ground ground = new Ground();
        addObject(ground, getWidth() / 2, getHeight()-20);
        // Set paint order
        setPaintOrder(Score.class, Start.class, Title.class, getReady.class, GameOver.class, Ground.class, bottomPipe.class, Pipe.class, FlappyBird.class);
        // Create a Flappy Bird object.
        FlappyBird flappy = new FlappyBird();
        
        // Add flappy to world
        addObject(flappy, 100, getHeight() / 2);

        // Creates a Score object and adds it to the world
        scoreObj = new Score();
        scoreObj.setScore(0);
        addObject(scoreObj, getWidth()/2, 50);

        // Creates and adds the Title object
        title = new Title();
        addObject(title, getWidth() / 2, getHeight() / 3);
        
        // Creates and adds the Start Button object
        startButton = new Start();
        addObject(startButton, getWidth() / 2, getHeight() / 2);
        
        // Creates and adds the Get Ready object
        press = new getReady();
        
        point = new GreenfootSound("Point.wav");
        Greenfoot.start();
    }
    /**
     * Act method that sets up the world
     */
    public void act()
    {
        /**
         * If statement that removes the start page
         * once player clicks on the start button.
         */
        if(Greenfoot.mouseClicked(startButton))
        {
            removeObject(title);
            removeObject(startButton);
            addObject(press, getWidth() / 2, getHeight() / 2);
            startPressed = true;
        }
        /**
         * If statement that calls method scoreCount if the world is not
         * paused.
         */
        if(isPaused == false)
        {
            scoreCount();
        }
        /**
         * If statement that plays the game if boolean startPressed is true
         */
        playGame();
    }
    /**
     * Method createPipe that adds the pipes.
     */
    private void createPipe() 
    {
        Pipe topPipe = new Pipe();
        bottomPipe botPipe = new bottomPipe();
        GreenfootImage topPipePic = topPipe.getImage();
        GreenfootImage bottomPipePic = botPipe.getImage();
        int dist = Greenfoot.getRandomNumber(200);
        int space = Greenfoot.getRandomNumber(75);
        addObject(topPipe, getWidth(), getHeight() / 2 - topPipePic.getHeight()/2 - 150 + dist - space);
        addObject(botPipe, getWidth(), getHeight()/2 + bottomPipePic.getHeight()/2 -75 + dist);
    }
    /**
     * Method playGame that starts the game.
     */
    private void playGame()
    {
        if(Greenfoot.isKeyDown("space"))
        {
            removeObject(press);
            isPaused = false;
        }
        pauseGame();
    }
    /**
     * Method scoreCount that calls method createPipe, creates the ground image, and
     * increases the score counter
     */
    private void scoreCount()
    {
        pipeCounter++;
        if(pipeCounter % 100 == 0)
        {
            createPipe();
        }
        if (pipeCounter >= FIRST_PIPE)
        {
            if(flappyCounter % 100 == 0)
            {
                point.play();
                score++;
                scoreObj.setScore(score);
            }
            flappyCounter++;
        }
        if (pipeCounter % 50 == 0)
        {
            Ground ground = new Ground();
            addObject(ground, getWidth(), getHeight() - 20);
        }
    }
    /**
     * Method pauseGame pauses the game
     */
    private void pauseGame()
    {
        if(startPressed == true)
        {
            if(isPaused == false)
            {
                if(Greenfoot.isKeyDown("p"))
                {
                    isPaused = true;
                }
                else
                {
                    if(Greenfoot.isKeyDown("space"))
                    {
                        isPaused = false;
                    }
                }
            }
        }
    }
    /**
     * Method getScore returns the player's score
     */
    public int getScore()
    {
        return score;
    }
}
