import java.util.*;

public class WordCountConsole extends WordCount
{

	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a word/sentence: ");
		String word = input.nextLine();
		HashMap<String, Integer> wordCount = new HashMap<String, Integer>();
		String[] words = word.split(" ");
        for(int i = 0; i < words.length; i++)
        {
            Integer num = wordCount.get(words[i].toLowerCase());
            if(num == null)
            {
                num = Integer.valueOf(1);
            }
            else
            {
                num = Integer.valueOf(num.intValue() + 1);
            }
            wordCount.put(words[i].toLowerCase(), num);
        }
        printNumWord(wordCount);
        input.close();
	}
}
