import java.util.Scanner;
public class Blackjack 
{
    private static final int HEARTS = 0;
    private static final int DIAMONDS = 1;
    private static final int SPADES = 2;
    private static final int CLUBS = 3;

    private static final int JACK = 11;
    private static final int QUEEN = 12;
    private static final int KING = 13;
    private static final int ACE = 14;
    
    // The starting bankroll for the player.
    private static final int STARTING_BANKROLL = 100;

    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args)
    {
        double bankroll = STARTING_BANKROLL;
        System.out.println("Starting bankroll: " + bankroll);
        while(true)
        {
            bankroll = playRound(bankroll);
            System.out.print("Would you like to play again? (Y/N)");
            String playAgain = scan.nextLine();
            if(playAgain.equalsIgnoreCase("N"))
            {
                break;
            }
        }
        System.out.println("Thanks for playing!");
    }

    static String getPlayerMove()
    {
        System.out.println("Enter move (hit / stand): ");
        while(true)
        {
            String move = scan.nextLine();
            move = move.toLowerCase();
            if(move.equals("hit") || move.equals("stand"))
            {
                return move;
            }  
        }
    }

    static void dealerTurn(Hand dealer, Deck deck)
    {
        String[] arr = {"Clubs", "Diamonds", "Hearts", "Spades"};
        while(true)
        {
            System.out.println("Dealer's hand");
            System.out.println(dealer);
            
            int value = dealer.getValue();
            System.out.println("Dealer's hand has value " + value);
            
            //readLine("Enter to continue...");
            System.out.println("Enter to continue...");
            String x = scan.nextLine();
            
            if(value < 17)
            {
                System.out.println("Dealer hits");
                Card c = deck.deal();
                dealer.addCard(c);
                
                System.out.println("Dealer card was " + c);
                
                if(dealer.busted())
                {
                    System.out.println("Dealer busted!");
                    break;
                }
            }
            else
            {
                System.out.println("Dealer stands.");
                break;
            }
            if(value == ACE)
            {
                while(true)
                {
                    System.out.println("Dealer's hand was an ACE.");
                    System.out.println("Would you like to play a side bet? (Y/N) ");
                    String bet = scan.nextLine();
                    if(bet.toUpperCase().equals("Y"))
                    {
                        while(true)
                        {
                            System.out.println("Guess the card suit (Clubs, Diamonds, Hearts, Spades) ");
                            int randVal = (int)(Math.random() * arr.length);
                            String guessSuit = scan.nextLine();
                            if(guessSuit.equalsIgnoreCase(arr[randVal]))
                            {
                                System.out.println("CORRECT!");
                                break;
                            }
                        }
                    } 
                }
            }
        }
    }

    public static boolean playerTurn(Hand player, Deck deck)
    {
        while(true)
        {
            String move = getPlayerMove();
            if(move.equals("hit"))
            {
                Card c = deck.deal();
                System.out.println("Your card was: " + c);
                player.addCard(c);
                System.out.println("Player's hand");
                System.out.println(player);
                if(player.busted())
                {
                    return true;
                }
            }
            else
            {
                // If we didn't hit, the player chose to 
                // stand, which means the turn is over.
                return false;
            }
        }
    }

    public static boolean playerWins(Hand player, Hand dealer)
    {
        if(player.busted())
        {
            return false;
        }
        if(dealer.busted())
        {
            return true;
        }
        return player.getValue() > dealer.getValue();
    }

    public static boolean push(Hand player, Hand dealer)
    {
        return player.getValue() == dealer.getValue();
    }

    public static double findWinner(Hand dealer, Hand player, int bet)
    {
        if(playerWins(player, dealer))
        {
            System.out.println("Player wins!");
            if(player.hasBlackjack())
            {
                return 1.5 * bet;
            }
            return bet;
        }
        else if(push(player, dealer))
        {
            System.out.println("You push");
            return 0;
        }
        else
        {
            System.out.println("Dealer wins");
            return -bet;
        }
    }

    public static double playRound(double bankroll)
    {
        System.out.print("What is your bet? ");
        int bet = scan.nextInt();

        Deck deck = new Deck();
        deck.shuffle();
        
        Hand player = new Hand();
        Hand dealer = new Hand();
        
        player.addCard(deck.deal());
        dealer.addCard(deck.deal());
        player.addCard(deck.deal());
        dealer.addCard(deck.deal());
        
        System.out.println("Player's Hand");
        System.out.println(player);
        
        
        System.out.println("Dealer's hand");
        dealer.printDealerHand();
        
        boolean playerBusted = playerTurn(player, deck);
        
        if(playerBusted)
        {
            System.out.println("You busted :(");
        }
        System.out.println("Enter for dealer turn...");
        String x = scan.nextLine();
        //readLine("Enter for dealer turn...");
        dealerTurn(dealer, deck);
        double bankrollChange = findWinner(dealer, player, bet);
        bankroll += bankrollChange;
        System.out.println("New bankroll: " + bankroll);
        return bankroll;
    }
}
