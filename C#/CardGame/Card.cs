using System;

namespace CardGame
{
    class Card
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

        public static String[] elements = {"Fire", "Earth", "Water", "Air"};
        public static String[] colors = {"red", "orange", "yellow", "green", "blue", "purple", "black", "white"};

        int element;
        int value;
        int color;

        // Constructor for the Card class.
        // Takes in the element, value, and
        // color parameter
        public Card(int element, int value, int color)
        {
            this.element = element;
            this.value = value;
            this.color = color;
        }

        // GetElement returns the int element variable
        public int GetElement()
        {
            return element;
        }

        // GetValue returns the int value variable
        public int GetValue()
        {
            return value;
        }

        // GetColor returns the int color variable
        public int GetColor()
        {
            return color;
        }

        // GetArticle fixes the grammar if the color
        // is orange
        public String GetArticle()
        {
            if(color == ORANGE)
            {
                return "an";
            }
            return "a";
        }
        // Returns an element from the elements array
        public String GetElementFromArray()
        {
            return elements[element];
        }

        // Returns a value as a string
        public String GetValueToString()
        {
            return value.ToString();
        }

        // Returns a color from the colors array
        public String GetColorFromArray()
        {
            return colors[color];
        }

        public String toString()
        {
            return GetColorFromArray() + " " + GetElementFromArray() + " card of value " + GetValueToString();
        }
    }
}
