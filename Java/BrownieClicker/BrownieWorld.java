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
    private Score playerScore; // Player's score
    private Brownie clickableBrownie; // Clickable brownie
    private GrandmaBtn grannyBtn; // Granny upgrade
    private FactoryBtn factorialBtn; // Factory upgrade
    private ClickBtn autoclickBtn; // Autoclicker upgrade 
    private UpgradeDisplay grandmaCounter; // Granny upgrade counter
    private UpgradeDisplay factoryCounter; // Factory upgrade counter
    private UpgradeDisplay autoclickCounter; // Autoclicker upgrade counter
    public UserInfo playerInfo = UserInfo.getMyInfo(); // Player info
    private SaveBtn save = new SaveBtn(); // Sabe button;
    private int score = 0; // Player score
    private int gCount = 0; // starting value of grandma upgrade
    private int fCount = 0; // starting value of factory upgrade 
    private int cCount = 0; // starting value of autoclick upgrade
    boolean startPressed = false; // Game start
    Start startBtn = new Start(); // Start btn object
    Title screen = new Title(); // Screen object
    InfoBtn info = new InfoBtn(); // Info button
    BackBtn back = new BackBtn(); // Back button
    BackToMenu backMenu = new BackToMenu(); // Back to menu button
    Instruction step = new Instruction(); // Instruction screen
    /**
     * Constructor for objects of class BrownieWorld
     * 
     */
    public BrownieWorld()
    {    
        // Create a new world with 700x500 cells with a cell size of 1x1 pixels.
        super(750, 550, 1);
        Greenfoot.start();
        setPaintOrder(BrownieWorld.class, Start.class, InfoBtn.class, Title.class, BackBtn.class, Instruction.class, Score.class, UpgradeDisplay.class, Brownie.class, Button.class, Container.class, BackToMenu.class, SaveBtn.class, MiniBrownies.class);
        // Brownie
        clickableBrownie = new Brownie();
        addObject(clickableBrownie, getWidth() / 2, getHeight() / 2);
        // Score
        playerScore = new Score();
        playerScore.setPlayerScore(score);
        addObject(playerScore, getWidth() / 2 , 30);
        addButtons(); // Buttons
        titleScreen(); // Title Screen
    }
    public void act()
    {
        // Takes players to the info screen
        if(Greenfoot.mouseClicked(info))
        {
            removeObject(info);
            removeObject(startBtn);
            removeObject(screen);
            addObject(step, getWidth() / 2, getHeight() / 2);
            addObject(back, getWidth() / 2, 500);
        }
        // Takes players back to the menu screen
        if(Greenfoot.mouseClicked(back) || Greenfoot.mouseClicked(backMenu))
        {
            Greenfoot.setWorld(new BrownieWorld());
        }
        // Removes the title screen and starts the game
        if(Greenfoot.mouseClicked(startBtn))
        {
            startPressed = true;
            loadPlayerData();
            removeObject(startBtn);
            removeObject(info);
            removeObject(screen);
            removeObject(step);
            addObject(save, getWidth() / 2, getHeight() - 40);
            addObject(backMenu, (getWidth() / 2) - 340, getHeight() - 35);
        }
        // Saves the player's data
        if(Greenfoot.mouseClicked(save))
        {
            savePlayerData();
        }
        if(Greenfoot.mouseClicked(backMenu))
        {
            saveCurrentData();
        }
        endSimulation();
    }
    /**
     * Method titleScreen displays what the user will
     * see when they open up the game
     */
    private void titleScreen()
    {        
        addObject(screen, getWidth() / 2, getHeight() / 2); // Title Screen
        addObject(startBtn, getWidth() / 2, getHeight() / 2); // Start button
        addObject(info, getWidth() / 2, (getHeight() / 2) + 80); // Info button
    }
    /**
     * Method loadPlayerData loads the player's saved data
     * i.e. their score and upgrades
     */
    public void loadPlayerData()
    {
        // Score
        score = playerInfo.getScore();
        playerScore.setPlayerScore(score);
        // Grandma
        gCount = playerInfo.getInt(0);
        grandmaCounter.setUpgrade(gCount);
        // Factory
        fCount = playerInfo.getInt(1);
        factoryCounter.setUpgrade(fCount);
        // Autoclicker
        cCount = playerInfo.getInt(2);
        autoclickCounter.setUpgrade(cCount); 
    }
    /**
     * Method endSimulation will end the game once the player has reach
     * the max int value 
     */
    private void endSimulation()
    {
        if(playerScore.getPoint() == 2147483647)
        {
            Greenfoot.playSound("Complete.wav");
            Greenfoot.setWorld(new EndCard());
            score = 0;
            playerScore.setPlayerScore(score);
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
        addObject(box1, 65, 115);
        // Grandma button
        grannyBtn = new GrandmaBtn();
        addObject(grannyBtn, 50, 65);
        // Upgrade counter
        grandmaCounter = new UpgradeDisplay();
        grandmaCounter.setUpgrade(gCount);
        addObject(grandmaCounter, getWidth() - 40, 65);
    }
    /**
     * Method factoryUpgradeCounter keeps track of how many
     * grandmas purchased
     */
    private void factoryUpgradeCounter()
    {
        // Container
        Container box2 = new Container();
        addObject(box2, 65, 225);
        // Factory button
        factorialBtn = new FactoryBtn();
        addObject(factorialBtn, 50, 175);
        // Upgrade counter
        factoryCounter = new UpgradeDisplay();
        factoryCounter.setUpgrade(fCount);
        addObject(factoryCounter, getWidth() - 40, 175);
    }
    /**
     * Method factoryUpgradeCounter keeps track of how many
     * grandmas purchased
     */
    private void autoclickUpgradeCounter()
    {   
        // Container
        Container box3 = new Container();
        addObject(box3, 65, 335);
        // Autoclicker button
        autoclickBtn = new ClickBtn();
        addObject(autoclickBtn, 50, 285);
        // Upgrade counter
        autoclickCounter = new UpgradeDisplay();
        autoclickCounter.setUpgrade(cCount);
        addObject(autoclickCounter, getWidth() - 40, 285);
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
     * Method savePlayerData will store the player's score and upgrades
     */
    public void savePlayerData()
    {
        if(UserInfo.isStorageAvailable())
        {
            if(playerScore.getPoint() > playerInfo.getScore())
            {
                playerInfo.setScore(playerScore.getPoint());
            }
            if(grandmaCounter.getPlayerUpgrade() > playerInfo.getInt(0))
            {
                playerInfo.setInt(0, grandmaCounter.getPlayerUpgrade());
            }
            if(factoryCounter.getPlayerUpgrade() > playerInfo.getInt(1))
            {
                playerInfo.setInt(1, factoryCounter.getPlayerUpgrade());
            }
            if(autoclickCounter.getPlayerUpgrade() > playerInfo.getInt(2))
            {
                playerInfo.setInt(2, autoclickCounter.getPlayerUpgrade());
            }
            playerInfo.store(); // Save to server
        }
    }
    /**
     * Overwrites the player's data if they make a purchase
     * or exit
     */
    public void saveCurrentData()
    {
        if(UserInfo.isStorageAvailable())
        {
            playerInfo.setScore(playerScore.getPoint());
            playerInfo.setInt(0, grandmaCounter.getPlayerUpgrade());
            playerInfo.setInt(1, factoryCounter.getPlayerUpgrade());
            playerInfo.setInt(2, autoclickCounter.getPlayerUpgrade());
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
        return playerScore;
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
    public UserInfo getPlayerInfo()
    {
        return playerInfo;
    }
}
