import java.util.ArrayList;

public class BookFilter 
{
	/**
	 * Filters out any books that do not belong to 
	 * the author
	 * 
	 * @param readingList
	 * @param author
	 * @return the remaining ArrayList containing only the books created by the author.
	 */
	public static ArrayList<Book> filterBooks(ArrayList<Book> readingList, String author)
	{
	    for(int i = readingList.size() - 1; i >= 0; i--) 
		{
	        if(!readingList.get(i).getAuthor().equals(author))
			{
	            readingList.remove(i);
			}
		}
	    return readingList;
	}

}
