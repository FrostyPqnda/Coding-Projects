using System.Collections.Generic;
using System.Linq;
using System;

namespace CardGame
{
    class Deck
    {
        public static readonly int FIRE = 0;
        public static readonly int EARTH = 1;
        public static readonly int WATER = 2;
        public static readonly int AIR = 3;

        public static readonly int RED = 0;
        public static readonly int ORANGE = 1;
        public static readonly int YELLOW = 2;
        public static readonly int GREEN = 3;
        public static readonly int BLUE = 4;
        public static readonly int PURPLE = 5;
        public static readonly int BLACK = 6;
        public static readonly int WHITE = 7;

        private List<Card> deck;
        Random rand = new Random();

        // Deck class constructor
        // Sets the deck variable to a new list that takes in the Card object
        public Deck()
        {
            deck = new List<Card>();
        }

        // CreateCards will create new cards with randomized value, element, and color
        public void CreateCards()
        {
            for(int value = 2; value <= 16; value++)
            {
                for(int element = FIRE; element <= AIR; element++)
                {
                    int color = rand.Next(WHITE + 1);
                    Card card = new Card(element, value, color);
                    deck.Add(card);
                }
            }

            ShuffleDeck();
        }

        // GetCardsFromDeck returns the deck list
        public List<Card> GetCardsFromDeck()
        {
            return deck;
        }
        
        // DealCard will create new cards if
        // the deck is empty and remove the
        // 0th index of the deck.
        public void DealCard()
        {
            if(!deck.Any())
            {
                CreateCards();
            }

            deck.RemoveAt(0);
        }

        // ShuffleDeck randomizes and sets the deck of cards
        public void ShuffleDeck()
        {
            for(int i = 0; i < deck.Count; i++)
            {
                int index = rand.Next(deck.Count);
                Card x = deck.ElementAt(i);
                Card y = deck.ElementAt(index);
                
                deck.Insert(i, y);
                deck.Insert(index, x);
            }
        }
    }
}