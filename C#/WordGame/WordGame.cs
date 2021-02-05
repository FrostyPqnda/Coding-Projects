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

            Console.Write("Guess the word [The hidden word has " + word.GetWord().Length + " letters]: ");
            String guess = Console.ReadLine();

            while(run) 
            {
                if(guess.Length >= word.GetWord().Length) {
                    Console.WriteLine(word.GetClue(guess));
                } else {
                    Console.WriteLine("\nThe length of your input was shorter than the length of the hidden word!");
                }
                    
                Console.Write("\nGuess the word [The hidden word has " + word.GetWord().Length + " letters]: ");
                guess = Console.ReadLine();

                if(guess.Equals(word.GetWord(), StringComparison.OrdinalIgnoreCase)) {
                    run = false;
                    Console.WriteLine(word);
                }
            }

            Console.ReadKey();
        }
    }
}
