/**
 * @author brian
 *
 * Book class - contains the constructor for the Book object,
 * the author and title
 */
public class Book
{
    private String title; // name of book
    private String author; // name of writer
    
    public Book(String theTitle, String theAuthor)
    {
        title = theTitle;
        author = theAuthor;
    }
    
    // Retrieves the title of the book
    public String getTitle()
    {
        return title;
    }
    
    // Retrieves the author of the book
    public String getAuthor()
    {
        return author;
    }
    
    // Prints the book and author
    public String toString()
    {
        return title + " by " + author;
    }
    
    // Compares two books
    public boolean equals(Book other)
    {
        return title.equals(other.title) && author.equals(other.author);
    }
}
