/**
 * 
 * @author brian
 * 
 * WordCount is a java program that counts how many
 * times a certain word appears from a text
 */

import java.util.*;

public class WordCount
{
	/**
	 * Public static void method that takes in the HashMaps
	 * of strings and integers to output the number of times
	 * a word appears
	 */
	public static void printNumWord(HashMap<String, Integer> printWord)
	{
		Object[] keys = printWord.keySet().toArray();
		Arrays.sort(keys);
		for(Object word : keys)
			System.out.println(word + ": " + printWord.get(word));
	}
}
