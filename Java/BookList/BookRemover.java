import java.util.ArrayList;
import java.util.Random;
/**
 * @author brian
 *
 * Java program that filters out a random book from a
 * list of books.
 */
public class BookRemover extends BookFilter
{
	public static void main(String[] args) 
	{
		Random rand = new Random();
		
		ArrayList<Book> bookList = new ArrayList<Book>();
		Book b1 = new Book("Animal Farms", "George Orwell");
		Book b2 = new Book("Lord of the Flies", "William Golding");
		Book b3 = new Book("The Chronicles of Narnia", "C.S. Lewis");
		Book b4 = new Book("The Hobbit", "J.R.R. Tolkien");
		Book b5 = new Book("The Lord of the Ring", "J.R.R. Tolkien");
		Book b6 = new Book("Farenheit 451", "Ray Bradbury");

		bookList.add(b1);
		bookList.add(b2);
		bookList.add(b3);
		bookList.add(b4);
		bookList.add(b5);
		bookList.add(b6);

		int random = rand.nextInt(bookList.size());

		ArrayList<Book> removeAuthors = filterBooks(bookList, bookList.get(random).getAuthor());
		System.out.println("Remaining book(s): " + removeAuthors);	
	}

}