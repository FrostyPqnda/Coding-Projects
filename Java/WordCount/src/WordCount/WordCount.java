package WordCount;
/*
 * This is a Java program that counts how many time a word from
 * the user's input appears.
 */
import java.util.*;

public class WordCount
{
	/*
	 * Public static void method that takes in the HashMaps
	 * of strings and integers to output the number of times
	 * a word appears
	 */
	public static void printNumWord(HashMap<String, Integer> printWord)
  {
      Object[] keys = printWord.keySet().toArray();
      Arrays.sort(keys);
      for(Object word : keys)
      {
          int value = printWord.get(word);
          System.out.println(word + ": " + value);
      }
  }
}
