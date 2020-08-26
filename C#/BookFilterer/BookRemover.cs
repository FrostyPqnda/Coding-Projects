using System;
using System.Linq;
using System.Collections.Generic;
using BookFilterer;

namespace BookFilterer
{
    /*
        The BookRemover class is where the books will get filtered.
    */
    public class BookRemover
    {
        String title;
        String writer;

        /*  Constructor for the BookRemover class
            Creates a Book object and sets the
            instance variable title and writer 
            to the GetBook() and GetAuthor
        */
        public BookRemover()
        {
            Book bookObj = new Book(writer, title);
            title = bookObj.GetBook();
            writer = bookObj.GetAuthor();
        }

        // FilterBook takes in @param readingList and author
        // to remove a book
        public List<Book> FilterBook(List<Book> readingList, String author)
        {
            // Loops through the entire list of books for any items
            for(int i = readingList.Count - 1; i >= 0; i--)
            {
                // Removes a book from the list if a book's author does not equal
                // @param author
                if(!readingList.ElementAt(i).GetAuthor().Equals(author))
                {
                    readingList.RemoveAt(i);
                }
            }

            //Console.WriteLine(title + " by " + writer + " has been removed.");
            return readingList;
            
        }

        /*
        public override String ToString() 
        {
            return title + " by " + writer + " has been removed.";
        }
        */
    }
}