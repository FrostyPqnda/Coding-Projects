import java.util.ArrayList;
import java.util.List;
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
		Book b5 = new Book("Farenheit 451", "Ray Bradbury");

		bookList.add(b1);
		bookList.add(b2);
		bookList.add(b3);
		bookList.add(b4);
		bookList.add(b5);

		String[] authors = {"George Orwell", "William Golding", "C.S. Lewis", "J.R.R. Tolkien", "Ray Bradbury"};
		int random = rand.nextInt(authors.length);

		List<Book> removeAuthors = filterBooks(bookList, authors[random])
		System.out.println(removeAuthors);	
	}

}
