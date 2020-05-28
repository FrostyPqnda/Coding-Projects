import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class BrownieWorld contains all of the objects of the actor class
 * and controls the game
 * 
 * @author Brian P. 
 * @version 18 March 2020
 */
public class BrownieWorld extends World
{
    private Score scoreObj; // How many brownies clicked
    private Brownie clickableBrownie; // Clickable brownie
    private GrandmaBtn grannyBtn; // Granny upgrade
    private FactoryBtn factorialBtn; // Factory upgrade
    private ClickBtn autoclick; // Autoclicker upgrade 
    private UpgradeDisplay grandmaCounter; // Granny upgrade counter
    private UpgradeDisplay factoryCounter; // Factory upgrade counter
    private UpgradeDisplay autoclickCounter; // Autoclicker upgrade counter
    public UserInfo playerInfo; // Save score
    private int score = 0; // Player score
    private SaveBtn save = new SaveBtn(); // Sabe button;
    private int gCount; // starting value of grandma upgrade
    private int fCount; // starting value of factory upgrade 
    private int cCount; // starting value of autoclick upgrade
    boolean startPressed = false; // Game start
    boolean isPaused = true; // Game paused
    boolean pressedSave = false; // checks if save btn has been pressed
    Start startBtn = null; // Start btn object
    Title screen = null; // Screen object
    InfoBtn info = null; // Info button
    BackBtn back = new BackBtn(); // Back button
    BackToMenu backMenu = new BackToMenu(); // Back to menu button
    Instruction step = new Instruction(); // Instruction scree
    /**
     * Constructor for objects of class BrownieWorld
     * 
     */
    public BrownieWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        Greenfoot.start();
        setPaintOrder(BrownieWorld.class, Start.class, InfoBtn.class, Title.class, BackBtn.class, Instruction.class, Score.class, UpgradeDisplay.class, Brownie.class, Button.class, Container.class, BackToMenu.class, SaveBtn.class, MiniBrownies.class);
        // Brownie
        clickableBrownie = new Brownie();
        addObject(clickableBrownie, getWidth() / 2, getHeight() / 2);
        // Score
        scoreObj = new Score();
        scoreObj.setPlayerScore(score);
        addObject(scoreObj, getWidth() / 2 , 30);
        addButtons(); // Buttons
        titleScreen(); // Title Screen
    }
    /**
     * Act - do whatever the BrownieWorld wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Takes players to the info screen
        if(Greenfoot.mouseClicked(info))
        {
            removeObject(info);
            removeObject(startBtn);
            removeObject(screen);
            addObject(step, getWidth() / 2, getHeight() / 2);
            addObject(back, getWidth() / 2, (getHeight() / 2) + 150);
        }
        // Takes players back to the menu screen
        if(Greenfoot.mouseClicked(back) || Greenfoot.mouseClicked(backMenu))
        {
            Greenfoot.setWorld(new BrownieWorld());
        }
        // Removes the start button and starts the game
        if(Greenfoot.mouseClicked(startBtn))
        {
            removeObject(startBtn);
            removeObject(info);
            removeObject(screen);
            startPressed = true;
            isPaused = false;
            removeObject(step);
            playerInfo.getMyInfo();
            addObject(save, getWidth() / 2, 363);
            addObject(backMenu, (getWidth() / 2) - 270, 370);
        }
        // Saves the player's score
        if(Greenfoot.mouseClicked(save))
        {
            saveScore();
        }
        try
        {
            reloadScore();
        }
        catch(java.lang.NullPointerException e)
        {
            System.out.println("ERROR!");
            e.printStackTrace();
        }
        if(Greenfoot.mouseClicked(backMenu))
        {
            saveCurrentScore();
        }
        endSimulation();
    }
    /**
     * Method titleScreen displays what the user will
     * see when they open up the game
     */
    private void titleScreen()
    {
        // Title Screen
        screen = new Title();
        addObject(screen, getWidth() / 2, getHeight() / 2);
        // Start button
        startBtn = new Start();
        addObject(startBtn, getWidth() / 2, getHeight() / 2);
        // Info button
        info = new InfoBtn();
        addObject(info, getWidth() / 2, (getHeight() / 2) + 80);
    }
    /**
     * Method reloadScore sets the current score to player score
     */
    public void reloadScore()
    {
        if(Greenfoot.isKeyDown("r") && pressedSave == false)
        {
            pressedSave = true;
            // Score
            score = playerInfo.getScore();
            scoreObj.setPlayerScore(score);
            
            gCount = playerInfo.getInt(0);
            fCount = playerInfo.getInt(1);
            cCount = playerInfo.getInt(2);
            
            grandmaCounter.setUpgrade(gCount);
            
            factoryCounter.setUpgrade(fCount);
            
            autoclickCounter.setUpgrade(cCount);
            
            //System.out.println(gCount + "," + fCount + "," + cCount);
        } 
    }
    /**
     * Method endSimulation will end the game once the player has reach
     * the max int value 
     */
    private void endSimulation()
    {
        if(scoreObj.getPoint() == 2147483647)
        {
            Greenfoot.playSound("Complete.wav");
            Greenfoot.setWorld(new EndCard());
            score = 0;
            scoreObj.setPlayerScore(score);
        }
    }
    /**
     * Method grannyUpgradeCounter keeps track of how many
     * grandmas purchased
     */
    private void grannyUpgradeCounter()
    {
        // Container
        Container box1 = new Container();
        addObject(box1, 65, 95);
        // Grandma button
        grannyBtn = new GrandmaBtn();
        addObject(grannyBtn, 50, 48);
        // Upgrade counter
        grandmaCounter = new UpgradeDisplay();
        grandmaCounter.setUpgrade(gCount);
        addObject(grandmaCounter, 560, 48);
    }
    /**
     * Method factoryUpgradeCounter keeps track of how many
     * grandmas purchased
     */
    private void factoryUpgradeCounter()
    {
        // Container
        Container box2 = new Container();
        addObject(box2, 65, 197);
        // Factory button
        factorialBtn = new FactoryBtn();
        addObject(factorialBtn, 50, 150);
        // Upgrade counter
        factoryCounter = new UpgradeDisplay();
        factoryCounter.setUpgrade(fCount);
        addObject(factoryCounter, 560, 150);
    }
    /**
     * Method factoryUpgradeCounter keeps track of how many
     * grandmas purchased
     */
    private void autoclickUpgradeCounter()
    {   
        // Container
        Container box3 = new Container();
        addObject(box3, 65, 297);
        // Autoclicker button
        autoclick = new ClickBtn();
        addObject(autoclick, 50, 247);
        // Upgrade counter
        autoclickCounter = new UpgradeDisplay();
        autoclickCounter.setUpgrade(cCount);
        addObject(autoclickCounter, 560, 247);
    }
    /**
     * Method addButton adds the buttons to the world
     */
    public void addButtons()
    {
        grannyUpgradeCounter();
        factoryUpgradeCounter();
        autoclickUpgradeCounter();
    }
    /**
     * Method saveScore saves the current score
     */
    public void saveScore()
    {
        if(UserInfo.isStorageAvailable())
        {
            playerInfo = UserInfo.getMyInfo();
            if(scoreObj.getPoint() > playerInfo.getScore())
            {
                playerInfo.setScore(scoreObj.getPoint());
            }
            playerInfo.setInt(0, grandmaCounter.getPlayerUpgrade());
            playerInfo.setInt(1, factoryCounter.getPlayerUpgrade());
            playerInfo.setInt(2, autoclickCounter.getPlayerUpgrade());
            playerInfo.store(); // Save to server
        }
    }
    /**
     * Overwrites the player's score if they make a purchase
     * or exit
     */
    public void saveCurrentScore()
    {
        if(UserInfo.isStorageAvailable())
        {
            playerInfo = UserInfo.getMyInfo();
            playerInfo.setScore(scoreObj.getPoint());
            playerInfo.store(); // Save to server
        }
    }
    /**
     * Method getBrownie returns the clickable
     * brownie
     */
    public Brownie getBrownie()
    {
        return clickableBrownie;
    }
    /**
     * Method getScore returns the score object
     */
    public Score getScore()
    {
        return scoreObj;
    }
    /**
     * Method getGrandmaCounter returns the grandmaCounter
     */
    public UpgradeDisplay getGrandmaCounter()
    {
        return grandmaCounter;
    }
    /**
     * Method getFactoryCounter returns the factoryCounter
     */
    public UpgradeDisplay getFactoryCounter()
    {
        return factoryCounter;
    }
    /**
     * Method getAutoclickCounter returns the factoryCounter
     */
    public UpgradeDisplay getAutoclickCounter()
    {
        return autoclickCounter;
    }
}
