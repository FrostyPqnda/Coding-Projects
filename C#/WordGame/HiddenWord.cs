using System;

namespace GuessWord
{
    public class HiddenWord
    {
        private string word; // Instance variable

        // Constructor of the HiddenWord class
        public HiddenWord()
        {   
            Random rand = new Random(); // Iniialize the Random class 
            String[] text = System.IO.File.ReadAllLines("WordList.txt"); // Creates a string array containing all the words from WordList.txt
            int randomIndex = rand.Next(text.Length); // Create a random number between all words in WordList.txt
            word = text[randomIndex]; // Sets the word to a random word from WordList.txt
        }

        // Gives the player hints of whether or not their input text is equal to the hidden word.
        public String GetClue(String text)
        {
            String hintStr = "";
            for(int i = 0; i < word.Length; i++)
            {
                if(text[i] == word[i])
                {
                    hintStr += text[i]; // Adds the character from @param text to the hintStr variable at the correct position
                }
                else if(word.IndexOf(text[i]) != -1)
                {
                    hintStr += '+'; // Adds a + if the letter exists in the word but not in the right position
                }
                else  
                {
                    hintStr += '*'; // Adds a * if the letter does not exist in the word
                }
            }
            return "\n" + hintStr;
        }

        // Accessor method (getter)
        public String GetWord()
        {
            return word;
        }

        // return the class object as a string
        public override String ToString()
        {
            return "\nThe hidden word was [" + word + "]!";
        }
    }
}