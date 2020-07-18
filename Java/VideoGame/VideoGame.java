/**
 * 
 * @author brian
 * 
 * Generates some rpg style story for a game
 */
public class VideoGame
{
    // Class variables go here:
    String title;
    String hero;
    String villian;
    String setting;
    String topScoreInitials;
    int topScore;
    int score;
    
    // Constructor for class VideoGame
    public VideoGame(String title, String hero, String villian, String setting)
    {
        this.title = title;
        this.hero = hero;
        this.villian = villian;
        this.setting = setting;
        
        topScoreInitials = "*";
        score = 0;
    }

    public String getTopScore()
    {
        return topScoreInitials;
    }
    
    public String setTopScore(String init, int score)
    {
        if (score > topScore)
        {
            return init + ": " + score; 
        }
        return topScoreInitials;
    }
    
    // ToString Method for class VideoGame
    public String toString()
    {
        return "In " + title + ", you, the hero " + hero + ", are on a quest to defeat the dreaded " + villian + " and save all of " + setting + ".";
    }
}

