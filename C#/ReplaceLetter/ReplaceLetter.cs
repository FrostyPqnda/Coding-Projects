using System;

namespace ReplaceLetter
{
    class ReplaceLetter
    {
        static void Main(string[] args)
        {
            Console.Title = "Replace Letter";
            Console.ForegroundColor = ConsoleColor.DarkCyan;

            Console.Write("Enter a word: ");
            string word = Console.ReadLine();

            Console.Write("\nEnter a letter to replace: ");
            char letterToReplace = Console.ReadKey().KeyChar;

            Console.Write("\n\nEnter the replacing letter: ");
            char replacingLetter = Console.ReadKey().KeyChar;

            Console.WriteLine("\n\nOriginal text: " + word);
            Console.WriteLine("\nNew text: " + ChangeLetter(word, letterToReplace, replacingLetter));

            Console.ReadKey();
        }
        
        static String ChangeLetter(String word, char letterToReplace, char replacingLetter)
        {
            String text = "";

            for(int i = 0; i < word.Length; i++)
                // If the letter to replace exists in the word, replace all occurence of it with the replacing letter
                if(word[i] == letterToReplace)
                    text += replacingLetter;
                else        
                    text += word[i];
            
            return text;
        }
    }
}
