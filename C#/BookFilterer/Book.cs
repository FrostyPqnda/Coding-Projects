using System;
using BookFilterer;

namespace BookFilterer
{
    /*
        The Book class contains all informations and methods that the Book
        object will use.
    */
    public class Book
    {
        // Instance variables for the Book class
        private String book;
        private String author;

        // Constructor for the Book class.
        // Takes @param String author and
        // String book.
        public Book(String book, String author)
        {
            this.book = book;
            this.author = author;
        }
        
        // GetBook returns the
        // book instance variable
        public String GetBook()
        {
            return book;
        }

        // GetAuthor returns the
        // author instance variable
        public String GetAuthor()
        {
            return author;
        }

        // Overrides the built-in ToString method
        // and prints out the author and book
        public override String ToString()
        {
            return book + " by " + author;
        }

        // IsEquals compares two book objects
        public Boolean IsEquals(Book other)
        {
            return book.Equals(other.book) && author.Equals(other.author);
        }
    }
}