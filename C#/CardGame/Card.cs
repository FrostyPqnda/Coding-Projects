using System;

namespace CardGame
{
    class Card
    {
        public static int FIRE = 0;
        public static int EARTH = 1;
        public static int WATER = 2;
        public static int AIR = 3;

        public static int RED = 0;
        public static int ORANGE = 1;
        public static int YELLOW = 2;
        public static int GREEN = 3;
        public static int BLUE = 4;
        public static int PURPLE = 5;

        public static String[] elements = {"Fire", "Earth", "Water", "Air"};
        public static String[] colors = {"red", "orange", "yellow", "green", "blue", "purple"};

        int element;
        int value;
        int color;

        public Card(int element, int value, int color)
        {
            this.element = element;
            this.value = value;
            this.color = color;
        }

        public int getElement()
        {
            return element;
        }

        public int getValue()
        {
            return value;
        }

        public int getColor()
        {
            return color;
        }

        public String getArticle()
        {
            if(color == ORANGE)
            {
                return "an";
            }
            return "a";
        }
        // Returns an element from the elements array
        public String getElementFromArray()
        {
            return elements[element];
        }

        // Returns a value as a string
        public String getStringValue()
        {
            return value.ToString();
        }

        // Returns a color from the colors array
        public String getColorFromArray()
        {
            return colors[color];
        }

        public String toString()
        {
            return getColorFromArray() + " " + getElementFromArray() + " card of value " + getStringValue();
        }
    }
}
