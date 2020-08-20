using System.Collections.Generic;
using System.Linq;
using System;

namespace CardGame
{
    class Dealer
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

        private List<Card> deal;

        public Dealer()
        {
            deal = new List<Card>();
        }

        public void AddCard(Card card)
        {
            deal.Add(card);
        }

        public Card GetCard(int element, int value)
        {
            for(int i = 0; i < deal.Count; i++)
            {
                if(deal.ElementAt(i).GetElement() == element && deal.ElementAt(i).GetValue() == value)
                {
                    return deal.ElementAt(i);
                }
            }

            return null;
        }

        public Card UseCard(int element, int value)
        {
            Card c = GetCard(element, value);

            if(c == null)
            {
                return null;
            } 

            deal.Remove(c);
            return c;
        }

        public Card UseCard(int index)
        {
            Card c = deal.ElementAt(index);
            deal.RemoveAt(index);
            return c;
        }

        public String ConvertToString()
        {
            String text = "";

            for(int i = 0; i < deal.Count; i++)
            {
                if(i == deal.Count - 1)
                {
                    text += "and " + deal.ElementAt(i).GetArticle() + " " + deal.ElementAt(i).ToString();
                }
                else
                {
                    text += deal.ElementAt(i).GetArticle() + " " + deal.ElementAt(i).ToString() + ", ";
                }
            }

            return text;
        }
    }
}