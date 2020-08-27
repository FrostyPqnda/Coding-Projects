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
        // RemoveBook takes in @param List<Book> readingList and 
        // String author to remove a book
        public List<Book> RemoveBook(List<Book> readingList, String author)
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

            return readingList;
        }
    }
}