public class Card
{
    private static final int HEARTS = 0;
    private static final int DIAMONDS = 1;
    private static final int SPADES = 2;
    private static final int CLUBS = 3;

    private static final int JACK = 11;
    private static final int QUEEN = 12;
    private static final int KING = 13;
    private static final int ACE = 14;

    private int rank;
    private int suit;
    private int value;

    private String[] ranks = {"X", "X", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    private String[] suits = {"H", "D", "S", "C"};

    public Card(int rank, int suit)
    {
        this.rank = rank;
        this.suit = suit;
    }

    public int getRank()
    {
        return rank;
    }

    public int getSuit()
    {
        return suit;
    }

    public int getValue()
    {
        value = rank;
        if(rank > 10)
        {
            value = 10;
        }
        
        if(rank == ACE)
        {
            value = 11;
        }  
        return value;
    }

    public String rankToString(int r)
    {
        return ranks[r];
    }

    public String suitToString(int s)
    {
        return suits[s];
    }
    
    public String getSuitAsString()
    {
        return suitToString(suit);
    }

    public String getRankAsString()
    {
        return rankToString(rank);
    }

    public String toString()
    {
        return ranks[rank] + suits[suit];
    }
}