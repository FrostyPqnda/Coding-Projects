import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Random;
/**
 * Class InvasionWorld stores all of the objects of the actor classes and
 * controls the game
 * 
 * @author Brian P.
 * @version 9 February 2020
 */
public class InvasionWorld extends World
{
    private int invaderLevel; // Variable that keeps track of how many times the invaders have descended
    private int timerSpeed; // Variable to control when the invaders' sound plays 
    private int fireSpeed; // Variable to control when the invaders shoot
    private int counter; // Counter variable for the world  
    private int sound; // Variable to control which sound plays for the invaders
    private Ship player;  // Variable for the player’s ship
    private int LAYERS = 6; // Constant for how many layers of invaders to create 
    private int COLUMNS = 6; // Constant for how many columns of invaders to 
    private int barrierWidth = 74; // Sets the width for the barrier
    private int barrierHeight = 25; // Sets the height for the barrier
    private int soundNumber = 1; // Controls the invader sound
    private int score; // Keeps track of number of invaders shot
    private int lives; // The amount of life the ship has
    boolean isPaused = true; 
    boolean startPressed = false;
    Start startButton = null;
    Score scoreObj = null;
    Score lifeObj = null;
    Title screen = null;
    /**
     * Constructor for InvasionWorld
     * 
     */
    public InvasionWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 600, 1, false); 
        // Sets the values for the global variables
        invaderLevel = 0;
        timerSpeed = 50;
        fireSpeed = 20;
        counter = 0;
        sound = 4;
        score = 0;
        lives = 3;
        player = new Ship();
        createInvader();
        createShip();
        int x = (getWidth() - barrierWidth * 4) / 4;
        for(int i = 0; i < 4; i++)
        {
            addBarriers((x / 2) + (barrierWidth + x) * i, getHeight() * 8 / 10 + barrierHeight);
        }
        // Creates a score object added to the world
        scoreObj = new Score();
        scoreObj.setScore(0);
        addObject(scoreObj, getWidth() - 870, getWidth() - 340);
        // Creates a lives object added to the world
        lifeObj = new Score();
        lifeObj.setLives(3);
        addObject(lifeObj, getWidth() - 98, getWidth() - 340);
        // Creates a title screen added to the world
        screen = new Title();
        addObject(screen, getWidth() / 2, getHeight() / 2);
        // Creates a start button object added to the world
        startButton = new Start();
        addObject(startButton, getWidth() / 2, getHeight() / 2);
        
        Greenfoot.start();
    }
    /**
     * Method invaderMovementSound plays
     * the invader movement wav files.
     */
    private void invaderMovementSound()
    {
        switch(soundNumber)
        {
            case 4:
            Greenfoot.playSound("fastinvader4.wav");
            soundNumber = 1;
            break;
            
            case 3:
            Greenfoot.playSound("fastinvader3.wav");
            soundNumber++;
            break;
            
            case 2:
            Greenfoot.playSound("fastinvader2.wav");
            soundNumber++;
            break;
            
            default:
            Greenfoot.playSound("fastinvader1.wav");
            soundNumber++;
        }
    }
    /**
     * Method createInvader adds the invaders to the world
     */
    public void createInvader()
    {
        SpaceInvader invader = new SpaceInvader();
        int invaderX = invader.getImage().getWidth(); // The x position of the invader
        int invaderY = invader.getImage().getHeight(); // The y position of the invader
        int xBuffer = invaderX/2;
        int yBuffer = invaderY/2;
        for(int row = 1; row <= LAYERS; row++)
        {
            for(int col = 1; col <= COLUMNS; col++)
            {
                SpaceInvader s = new SpaceInvader();
                int x = (invaderX + xBuffer) * col;
                int y = (invaderY + yBuffer) * row;
                if(row == 1 || row == 2)
                {
                    SpaceInvader AlienThree = new SpaceInvader(3, row, col);
                    addObject(AlienThree, x, y);
                }
                if(row == 3 || row == 4)
                {
                    SpaceInvader alienTwo = new SpaceInvader(2, row, col);
                    addObject(alienTwo, x, y);
                }
                if(row == 5 || row == 6)
                {
                    SpaceInvader alienOne = new SpaceInvader(1, row, col);
                    addObject(alienOne, x, y);
                }
            }
        }
    }   
    /**
     * Method createShip adds the player ship to the world
     */
    public void createShip()
    {
        Ship player = new Ship();
        int shipX = getWidth() / 2; // The x position of the player's ship
        int shipY = getHeight() * 9 / 10; // The y position of the player's ship
        addObject(player, shipX, shipY);
    }
    /**
     * Method createUFO adds the mothership to the world
     */
    public void createUFO()
    {
        if(Greenfoot.getRandomNumber(400) == 0)
        {
            UFO galaga = new UFO();
            addObject(galaga, 20, 100);
            Greenfoot.playSound("ufo_highpitch.wav");
        }
    }
    /**
     * Method getPlayer returns the player Ship variable
     */
    public Ship getPlayer()
    {
        return player;
    }
    /**
     * Method getTimerSpeed() returns
     * the timerSpeed variable
     */
    public int getTimerSpeed()
    {
        return timerSpeed;
    }
    /**
     * Method gameOver takes in
     * boolean playerWin as a 
     * parameter to check if
     * the player has win or
     * loss.
     */
    public void gameOver(boolean playerWin)
    {
        if(playerWin == false)
        {
            displayGameOver();
            Greenfoot.playSound("GO.wav");
        }
        else
        {
            displayWinImage();
            Greenfoot.playSound("Complete.wav");
        }
        isPaused = true;
    }
    /**
     * Method addBarrier adds barriers
     * to the world.
     */
    private void addBarriers(int x, int y)
    {
       int topHalf = y - barrierHeight / 2;
       for(int i = 0; i < barrierHeight / 2; i += 2)
       {
           for(int c = 0; c < barrierWidth; c += 2)
           {
               Barrier b = new Barrier();
               addObject(b, x + c, y - i);
           }
           for(int d = 0; d < barrierWidth - 2 * i; d += 2)
           {
               Barrier b = new Barrier();
               addObject(b, x + d, y - i);
           }
       }
       for(int i = 0; i < barrierHeight / 2; i += 2)
       {
           for(int g = 0; g < barrierWidth / 2 - 2 * i; g += 2)
           {
               Barrier ba = new Barrier();
               addObject(ba, x + g + barrierWidth / 2, topHalf - i);
               Barrier bb = new Barrier();
               addObject(bb, x - g + barrierWidth / 2, topHalf - i);
           }
       }
    }
    /**
     * Method checkRightEdge
     * checks the movement of
     * invaders on the right
     */
    private void checkRightEdge(java.util.List invaders)
    {
        int column = 0;
        SpaceInvader right = null;
        for(int i = 0; i < invaders.size(); i++)
        {
            SpaceInvader si = (SpaceInvader)invaders.get(i);
            if(si.getColumn() > column)
            {
                right = si;
                column = right.getColumn();
            }
        }
        if(right != null)
        {
            if(right.getX() + right.getSpeed() + right.getImage().getWidth() / 2 > getWidth() - right.getImage().getWidth())
            {
                invaderLevel++;
            }
        }
    }
    /**
     * Method checkLeftEdge
     * checks the movement
     * of invaders on the
     * left
     */
    private void checkLeftEdge(java.util.List invaders)
    {
        int column = 0;
        SpaceInvader left = null;
        for(int i = 0; i < invaders.size(); i++)
        {
            SpaceInvader si = (SpaceInvader)invaders.get(i);
            if(si.getColumn() > column)
            {
                left = si;
                column = left.getColumn();
            }
        }
        if(left != null)
        {
            if(left.getX() + left.getSpeed() + left.getImage().getWidth() / 2 < getWidth() / 2 - left.getImage().getWidth() / 2)
            {
                invaderLevel++;
            }
        }
    }
    /**
     * Method act
     * the actions of the InvasionWorld each tick
     */
    public void act()
    {
        if(Greenfoot.mouseClicked(startButton))
        {
            removeObject(startButton);
            removeObject(screen);
            startPressed = true;
            isPaused = false;
        }
        startGame();
        restartGame();
    }
    /**
     * Method restartGame resets the game when the 
     * "r" key is pressed
     */
    private void restartGame()
    {
        if(Greenfoot.isKeyDown("r"))
        {
            Greenfoot.setWorld(new InvasionWorld());
            Greenfoot.start();
        }
    }
    /**
     * method getInvaderLevel returns
     * the invader levels
     */
    public int getInvaderLevel()
    {
        return invaderLevel;
    }
    private SpaceInvader findLowestInvader(java.util.List invaders, int invaderCol)
    {
        SpaceInvader lowest = null;
        for(int i = 0; i < invaders.size(); i++)
        {
            SpaceInvader ss = (SpaceInvader)invaders.get(i);
            if(ss.getColumn() == invaderCol)
            {
                if(lowest == null || lowest.getLayer() < ss.getLayer())
                {
                    lowest = ss;
                }
            }
        }
        return lowest;
    }
    /**
     * Method getScore gets the score
     * and returns it
     */
    public int getScore()
    {
        return score;
    }
    /**
     * Method setScore sets the score
     * to @param newScore
     */
    public void setScore(int newScore)
    {
        score = newScore;
    }
    /**
     * Method getLives gets the life
     * of the ship and returns it
     */
    public int getLives()
    {
        return lives;
    }
    /**
     * Method setLives sets the life
     * to @param newLife
     */
    public void setLives(int newLife)
    {
        lives = newLife;
    }
    /**
     * Method setTimerSpeed sets
     * the timer speed to @param timer
     */
    public void setTimerSpeed(int timer)
    {
        timerSpeed = timer;
    }
    /**
     * Method displayGameOver 
     * displays the game over
     * image.
     */
    private void displayGameOver()
    {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        GameOver gameOver = new GameOver();
        gameOver.getImage().setTransparency(210);
        addObject(gameOver, centerX, centerY);
    }
    /**
     * Method displayWinImage
     * displays the you win
     * image
     */
    private void displayWinImage()
    {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        Victory youWin = new Victory();
        youWin.getImage().setTransparency(210);
        addObject(youWin, centerX, centerY);
    }
    /**
     * Method increaseSpeed increases
     * the rate in which the invader
     * moves
     */
    private void increaseSpeed(java.util.List invaders)
    {
        if(invaders.size() < 34 && invaders.size() > 30)
        {
            timerSpeed = 45;
        }
        else if(invaders.size() <= 30 && invaders.size() > 24)
        {
            timerSpeed = 35;
        }
        else if(invaders.size() <= 24 && invaders.size() > 18)
        {
            timerSpeed = 25;
        }
        else if(invaders.size() <= 18 && invaders.size() > 12)
        {
            timerSpeed = 15;
        }
        else if(invaders.size() <= 12)
        {
            timerSpeed = 10;
        }
    }
    /**
     * Method startGame starts the game after the
     * start button has been removed
     */
    private void startGame()
    {
        if(isPaused == false)
        {
            counter++;
            java.util.List invaders = getObjects(SpaceInvader.class);
            scoreObj.setScore(score);
            lifeObj.setLives(lives);
            if(invaders.size() > 0)
            {
                if(counter % timerSpeed == 0)
                {
                    invaderMovementSound();
                }
                if(invaderLevel % 2 == 0)
                {   
                    checkRightEdge(invaders);
                }
                else
                {
                    checkLeftEdge(invaders);
                }
                if(counter % fireSpeed == 0)
                {
                    int random = Greenfoot.getRandomNumber(COLUMNS + 1);
                    int random2 = Greenfoot.getRandomNumber(5);
                    for(int i = 0; i < random2; i++)
                    {
                        SpaceInvader si = findLowestInvader(invaders, random);
                        if(si != null)
                        {
                            si.fire();
                        }
                    }
                }
                increaseSpeed(invaders);
            }
            else
            {
                gameOver(true);
            }
            createUFO();
        }
    }
}
