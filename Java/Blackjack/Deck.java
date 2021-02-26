import java.util.ArrayList;
public class Deck 
{
    private static final int HEARTS = 0;
    private static final int DIAMONDS = 1;
    private static final int SPADES = 2;
    private static final int CLUBS = 3;

    private static final int JACK = 11;
    private static final int QUEEN = 12;
    private static final int KING = 13;
    private static final int ACE = 14;

    private ArrayList<Card> deck;

    public Deck()
    {
        deck = new ArrayList<Card>();
        for(int rank = 2; rank <= ACE; rank++)
        {
            for(int suit = HEARTS; suit <= CLUBS; suit++)
            {
                Card card = new Card(rank, suit);
                deck.add(card);
            }
        }
    }

    public ArrayList<Card> getCards()
    {
        return deck;
    }

    public Card deal()
    {
        return deck.remove(0);
    }

    public void print()
    {
        for(Card card : deck)
        {
            System.out.println(card);
        }
    }

    public void shuffle()
    {
        for(int i = 0; i < deck.size(); i++)
        {
            int randomIndex = (int)(Math.random() * 52);
            Card x = deck.get(i);
            Card y = deck.get(randomIndex);
            deck.set(i, y);
            deck.set(randomIndex, x);
        }
    }
}
