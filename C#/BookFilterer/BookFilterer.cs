using System;
using System.Collections.Generic;
using BookFilterer;

namespace BookFilterer
{
    /*
        C# project I have adapted from my Java project. This program filters out any
        books randomly.
    */
    public class BookFilter
    {
        static void Main(string[] args)
        {
            Random rand = new Random();

            List<Book> bookList = new List<Book>();
            BookRemover bookSweeper = new BookRemover();
        
            Book b1 = new Book("Animal Farms", "George Orwell");
		    Book b2 = new Book("Lord of the Flies", "William Golding");
		    Book b3 = new Book("The Chronicles of Narnia", "C.S. Lewis");
		    Book b4 = new Book("The Hobbit", "J.R.R. Tolkien");
		    Book b5 = new Book("Farenheit 451", "Ray Bradbury");

            bookList.Add(b1);
            bookList.Add(b2);
            bookList.Add(b3);
            bookList.Add(b4);
            bookList.Add(b5);
            
            String[] authors = {
                b1.GetAuthor(), b2.GetAuthor(), b3.GetAuthor(), b4.GetAuthor(), b5.GetAuthor()
            };
            int randomize = rand.Next(authors.Length);
            bookList = bookSweeper.RemoveBook(bookList, authors[randomize]);

            foreach(Book item in bookList)
            {
                Console.WriteLine(item + " has been removed from the Book list.");
            }

            Console.ReadKey();
        }
         
    }
}
