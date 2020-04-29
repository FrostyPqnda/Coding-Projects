/**
 * 
 * @author brian
 * 
 * Generates some rpg style story for a game
 */
public class VideoGame
{
    // Class variables go here:
    String gameName;
    String hero;
    String villian;
    String setting;
    String topScoreInitials;
    int topScore;
    int score;
    
    // Constructor for class VideoGame
    public VideoGame(String title, String protagonist, String enemy, String area)
    {
        gameName = title;
        hero = protagonist;
        villian = enemy;
        setting = area;
        
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
        return "In " + gameName + ", you, the hero " + hero + ", are on a quest to defeat the dreaded " + villian + " and save all of " + setting + ".";
    }
}

