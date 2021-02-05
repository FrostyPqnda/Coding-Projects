using System;
namespace GuessTheWord
{
    class GuessWord 
    {
        static void Main(string[] args)
        {
            Console.Title = "Word Game";
            Console.ForegroundColor = ConsoleColor.DarkCyan;
            
            WordGame word = new WordGame();
            bool run = true;

            while(run) 
            {
                Console.Write("Guess the word [The hidden word has " + word.GetWord().Length + " letters]: ");
                String guess = Console.ReadLine();
                if(guess.Length >= word.GetWord().Length) 
                {
                    Console.WriteLine(word.GetClue(guess));
                    Console.Write("Guess the word [The hidden word has " + word.GetWord().Length + " letters]: ");
                    guess = Console.ReadLine();

                    if(guess.Length < word.GetWord().Length) {
                        Console.WriteLine("The length of your input is shorter than the length of the hidden word!");
                    }

                    if(guess.Equals(word.GetWord(), StringComparison.OrdinalIgnoreCase)) {
                        run = false;
                        Console.WriteLine(word);
                    }
                } else {
                    Console.WriteLine("The length of your input is shorter than the length of the hidden word!");
                }
            }

            Console.ReadKey();
        }
    }
}