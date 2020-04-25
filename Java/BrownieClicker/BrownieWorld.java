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
    private Score grandmaCounter; // Granny upgrade counter
    private Score factoryCounter; // Factory upgrade counter
    private Score autoclickCounter; // Autoclicker upgrade counter
    public UserInfo scoreInfo; // Save score
    public UserInfo gInfo; // Saves the grandma upgrade
    public UserInfo fInfo; // Saves the factory upgrade
    public UserInfo cInfo; // Saves the autoclicker upgrade
    private int score = 0; // Player score
    private SaveBtn save = new SaveBtn(); // Sabe button;
    private int gCount, fCount, cCount = 0; // The starting value of the upgrades
    boolean startPressed = false; // Game start
    boolean isPaused = true; // Game paused
    Start startBtn = null; // Start btn object
    Title screen = null; // Screen object
    /**
    * Constructor for objects of class BrownieWorld.
     * 
     */
    public BrownieWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        Greenfoot.start();
        // Brownie
        clickableBrownie = new Brownie();
        addObject(clickableBrownie, getWidth() / 2, getHeight() / 2);
        // Score
        scoreObj = new Score();
        scoreObj.setScore(score);
        addObject(scoreObj, getWidth() / 2 , 30);
        addButtons(); // Buttons
        // Title Screen
        screen = new Title();
        addObject(screen, getWidth() / 2, getHeight() / 2);
        // Start button
        startBtn = new Start();
        addObject(startBtn, getWidth() / 2, getHeight() / 2);
    }
    /**
     * Act - do whatever the BrownieWorld wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(Greenfoot.mouseClicked(startBtn))
        {
            removeObject(startBtn);
            removeObject(screen);
            scoreInfo.getMyInfo();
            startPressed = true;
            isPaused = false;
            addObject(save, getWidth() / 2, 364);
        }  
        if(Greenfoot.mouseClicked(save))
        {
            saveScore();
            //saveGScore();
            //saveFScore();
            //saveCScore();
        }
        try
        {
            reloadScore();
        }
        catch(java.lang.NullPointerException exception)
        {
            System.out.println("======================");
            System.out.println("Press the save button\nthen the 'R' key to\nreload your score");
            System.out.println("======================");
        }
        endSimulation();
    }
    /**
     * Method reloadScore sets the current score to player score
     */
    public void reloadScore()
    {
        if(Greenfoot.isKeyDown("r"))
        {
            score = scoreInfo.getScore();
            scoreObj.setScore(score);
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
            scoreObj.setScore(0);
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
        addObject(box1, 65, 99);
        // Grandma button
        grannyBtn = new GrandmaBtn();
        addObject(grannyBtn, 50, 48);
        // Upgrade counter
        grandmaCounter = new Score();
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
        factoryCounter = new Score();
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
        addObject(box3, 65, 295);
        // Autoclicker button
        autoclick = new ClickBtn();
        addObject(autoclick, 50, 247);
        // Upgrade counter
        autoclickCounter = new Score();
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
            scoreInfo = UserInfo.getMyInfo();
            if(scoreObj.getPoint() > scoreInfo.getScore())
            {
                scoreInfo.setScore(scoreObj.getPoint());
                scoreInfo.store(); // Save to server
            }
        }
    }
    public void saveGScore()
    {
        if(UserInfo.isStorageAvailable())
        {
            gInfo = UserInfo.getMyInfo();
            if(grandmaCounter.getPlayerUpgrade() > gInfo.getScore())
            {
                gInfo.setScore(grandmaCounter.getPlayerUpgrade());
                gInfo.store();
            }
        }
    }
    public void saveFScore()
    {
        if(UserInfo.isStorageAvailable())
        {
            fInfo = UserInfo.getMyInfo();
            if(factoryCounter.getPlayerUpgrade() > fInfo.getScore())
            {
                fInfo.setScore(factoryCounter.getPlayerUpgrade());
                fInfo.store();
            }
        }
    }
    public void saveCScore()
    {
        if(UserInfo.isStorageAvailable())
        {
            cInfo = UserInfo.getMyInfo();
            if(autoclickCounter.getPlayerUpgrade() > cInfo.getScore())
            {
                cInfo.setScore(autoclickCounter.getPlayerUpgrade());
                cInfo.store();
            }
        }
    }
    /**
     * Literally exist so players don't cheat their scores
     */
    public void antiCheatScore()
    {
        if(UserInfo.isStorageAvailable())
        {
            scoreInfo = UserInfo.getMyInfo();
            scoreInfo.setScore(scoreObj.getPoint());
            scoreInfo.store(); // Save to server
        }
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
    public Score getGrandmaCounter()
    {
        return grandmaCounter;
    }
    /**
     * Method getFactoryCounter returns the factoryCounter
     */
    public Score getFactoryCounter()
    {
        return factoryCounter;
    }
    /**
     * Method getAutoclickCounter returns the factoryCounter
     */
    public Score getAutoclickCounter()
    {
        return autoclickCounter;
    }
}
