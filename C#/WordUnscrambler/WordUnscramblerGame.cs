using System;

namespace WordUnscrambler
{
    class WordUnscramblerGame
    {
        static void Main(string[] args)
        {
            Console.Title = "Word Unscrambler Game";
            Console.ForegroundColor = ConsoleColor.DarkCyan;

            WordScrambler scramble = new WordScrambler();
            bool run = true;
            int count = 0; // Counter variable checking how many tries it take to unscramble the word.
            Console.WriteLine(scramble);

            while(run)
            {
                Console.Write("\nUnscramble the given word [Enter -1 to quit]: ");
                String guessWord = Console.ReadLine();

                count++;

                if(guessWord.Length == scramble.GetUnscrambledWord().Length)
                {
                    Console.WriteLine("\nHint: " + scramble.GetClue(guessWord));
                }
                else if(guessWord.Equals("-1"))
                {
                    run = false;
                    Console.WriteLine("\nYOU HAVE FAILED TO UNSCRAMBLE THE WORD!");
                    Console.WriteLine("\nUnscrambled Word: " + scramble.GetUnscrambledWord());
                }
                else
                {
                    Console.WriteLine("\nThe length of your input was not equal to the length of the hidden word!");
                }

                if(guessWord.Equals(scramble.GetUnscrambledWord(), StringComparison.OrdinalIgnoreCase))
                {
                    run = false;
                    Console.WriteLine("\nYOU UNSCRAMBLED THE WORD!");
                    Console.WriteLine("\nNumber of tries to unscramble the word: " + count);
                }   
            }
        
            Console.ReadKey();
        }
    }
}