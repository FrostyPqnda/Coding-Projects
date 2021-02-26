import java.util.ArrayList;
public class Hand 
{
    private static final int HEARTS = 0;
    private static final int DIAMONDS = 1;
    private static final int SPADES = 2;
    private static final int CLUBS = 3;

    private static final int JACK = 11;
    private static final int QUEEN = 12;
    private static final int KING = 13;
    private static final int ACE = 14;

    private ArrayList<Card> cards;

    public Hand()
    {
        cards = new ArrayList<Card>();
    }

    public void addCard(Card c)
    {
        cards.add(c);
    }

    public int getValue()
    {
        int sum = 0;
        int aceCount = 0;
        for(Card c : cards)
        {
            sum += c.getValue();
            if(c.getRank() == ACE)
            {
                aceCount++;
            }
        }
        while(sum > 21 && aceCount > 0)
        {
            sum -= 10;
            aceCount--;
        }
        return sum;
    }

    public boolean hasBlackjack()
    {
        return getValue() == 21 && cards.size() == 2;
    }

    public boolean busted()
    {
        return getValue() > 21;
    }

    public boolean fiveCardCharlie()
    {
        return cards.size() == 5;
    }

    public void printDealerHand()
    {
        for(int i = 0; i < cards.size(); i++)
        {
            Card c = cards.get(i);
            
            if(i == 0)
            {
                System.out.print("X ");
            }
            else
            {
                System.out.print(c + " ");
            }
        }
        System.out.println();
    }

    public String toString()
    {
        String result = "";
        for(Card c : cards)
        {
            result += c + " ";
        }
        result += "(" + getValue() + ")";
        return result;
    }
}
