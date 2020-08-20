using System.Collections.Generic;
using System.Linq;
using System;
using System.Collections;

namespace CardGame
{
    class Storage
    {
        public static readonly int FIRE = 0;
        public static readonly int EARTH = 1;
        public static readonly int WATER = 2;
        public static readonly int AIR = 3;
        public static readonly int MIXED = 4;

        public static readonly int RED = 0;
        public static readonly int ORANGE = 1;
        public static readonly int YELLOW = 2;
        public static readonly int GREEN = 3;
        public static readonly int BLUE = 4;
        public static readonly int PURPLE = 5;
        public static readonly int BLACK = 6;
        public static readonly int WHITE = 7;

        readonly String[] elements = {"Fire", "Earth", "Water", "Air"};
        readonly String[] colors = {"red", "orange", "yellow", "green", "blue", "purple", "black", "white"};

        private List<int> storage = new List<int>();
        private int winElement;
        private List<int> winColors;

        public Storage()
        {
            winColors = new List<int>();
            for(int i = 0; i < storage.Count; i++)
            {
                //storage[i] = new List<int>() 
            }
        }

    }
}