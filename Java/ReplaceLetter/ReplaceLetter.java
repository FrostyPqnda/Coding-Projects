package ReplaceLetter;
public class ReplaceLetter 
{
	public static String replaceLetter(String word, char letterToReplace, char replacingLetter)
    {
        String letterReplacement = "";
        for(int i = 0; i < word.length(); i++)
        {
            if(word.charAt(i) == letterToReplace)
            {
                letterReplacement += replacingLetter;
            }
            else
            {
                letterReplacement += word.charAt(i);
            }
        }
        return letterReplacement;
    }
}
