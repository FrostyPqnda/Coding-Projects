using System;

namespace GuessWord
{
    class WordGame
    {
        static void Main(string[] args)
        {
            Console.Title = "Word Game";
            Console.ForegroundColor = ConsoleColor.DarkCyan;
            
            HiddenWord word = new HiddenWord();
            bool run = true;
            int tries = 0;

            //Console.Write("Guess the word [The hidden word has " + word.GetWord().Length + " letters]: ");
            //String guess = Console.ReadLine();

            while(run) 
            {
                Console.Write("Guess the word [The hidden word has " + word.GetWord().Length + " letters]: ");
                String guess = Console.ReadLine();
            
                if(guess.Length >= word.GetWord().Length)
                {
                    Console.WriteLine(word.GetClue(guess));
                    tries++;
                }
                else
                {
                    Console.WriteLine("\nThe length of your input was shorter than the length of the hidden word!");
                    tries++;
                }
                if(guess.Equals(word.GetWord(), StringComparison.OrdinalIgnoreCase)) {
                    run = false;
                    Console.WriteLine(word);
                    Console.WriteLine("Number of tries to guess the word: " + tries);
                }
            }
            Console.ReadKey();
        }
    }
}