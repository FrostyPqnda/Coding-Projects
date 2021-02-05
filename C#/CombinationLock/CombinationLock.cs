using System;

namespace CombinationLock
{
    class CombinationLock
    {
        private String word;

        public CombinationLock(String word)
        {
            this.word = word;
        }

        public String GetClue(String text)
        {
            String hintStr = "";
            
            for(int i = 0; i < word.Length; i++) {
                if(text[i] == word[i])
                    hintStr  += text[i];
                else if(word.IndexOf(text[i]) != -1)
                    hintStr += '+';
                else  
                    hintStr += '*';
            }

            return hintStr;
        }
    }
}
