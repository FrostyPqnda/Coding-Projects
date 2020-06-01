import java.util.List;
/**
 * @author brian
 *
 * BookFilter class - filters any books provided from the
 * Book class.
 * 
 * Removes any books not approved and only gives what has
 * been approved
 */
public class BookFilter 
{
	public static List<Book> filterBooks(List<Book> readingList, String author)
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
