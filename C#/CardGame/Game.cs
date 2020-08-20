using System;

namespace CardGame
{
    class Game
    {
        static void Main(String[] args)
        {
            Card fire = new Card(0, 11, 5);
            Console.WriteLine(fire.toString());

            Console.ReadKey();
        }
    }
}