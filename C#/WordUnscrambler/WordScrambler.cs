using System;

namespace WordUnscrambler
{   
    public class WordScrambler
    {
        // Instance variables
        private string scrambledWord;
        private string unscrambledWord;

        // Constructor for the WordScrambler class
        // Reads from a text file and then scrambles each word in the file.
        public WordScrambler()
        {
            Random rand = new Random(); // Iniialize the Random class 
            String[] text = System.IO.File.ReadAllLines("WordList.txt"); // Creates a string array containing all the words from WordList.txt
            int randomIndex = rand.Next(text.Length); // Create a random number between all words in WordList.txt
            unscrambledWord = text[randomIndex];
            scrambledWord = ScrambleWord(text[randomIndex]); // Sets the word to a random word from WordList.txt
        }

        // Scrambles the word in random order
        public string ScrambleWord(string str)
        {
            string scrambledWord = "";
            char[] arr = str.ToCharArray();
            Random rand = new Random();
            for(int i = 0; i < arr.Length - 1; i++)
            {
                int pos = rand.Next(i + 1, arr.Length - 1);
                char temp = arr[i];
                arr[i] = arr[pos];
                arr[pos] = temp;
            }
            scrambledWord = String.Join("", arr);
            return scrambledWord;
        }
        
        // Gives the player hints about the unscrambled hidden word
        public string GetClue(string str)
        {
            string hintStr = "";
            for(int i = 0; i < str.Length; i++)
            {
                if(str[i] == unscrambledWord[i])
                {
                    hintStr += str[i];
                }
                else if(unscrambledWord.IndexOf(str[i]) != -1)
                {
                    hintStr += '+';
                }
                else
                {
                    hintStr += '*';
                }
            }
            return hintStr;
        }

        // Gets the unscrambled word
        public string GetUnscrambledWord()
        {
            return unscrambledWord;
        }

        // Returns the WordScrambler object as a string
        public override string ToString()
        {
            return scrambledWord;
        }
    }
}
