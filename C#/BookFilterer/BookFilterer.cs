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
            // Creates a new Random object
            Random rand = new Random();

            // Creates a list that will take in the Book object
            List<Book> bookList = new List<Book>();
            // Creates an new BookRemover object that can access any methods or variables
            // inside the BookRemover class
            BookRemover bookSweeper = new BookRemover();

            // Creates 5 book object with different titles and authors
            Book b1 = new Book("Animal Farms", "George Orwell");
            Book b2 = new Book("Lord of the Flies", "William Golding");
            Book b3 = new Book("The Chronicles of Narnia", "C.S. Lewis");
            Book b4 = new Book("The Hobbit", "J.R.R. Tolkien");
            Book b5 = new Book("Farenheit 451", "Ray Bradbury");

            // Adds the book objects to the bookList
            bookList.Add(b1);
            bookList.Add(b2);
            bookList.Add(b3);
            bookList.Add(b4);
            bookList.Add(b5);
            
            // Creates a string array that stores the authors from the book objects
            String[] authors = {
                b1.GetAuthor(), b2.GetAuthor(), b3.GetAuthor(), b4.GetAuthor(), b5.GetAuthor()
            };
            // Randomizes the author based on the length of the authors array
            int randomize = rand.Next(authors.Length);
            // Sets the List variable bookList to the RemoveBook method from the BookRemover object
            bookList = bookSweeper.RemoveBook(bookList, authors[randomize]);

            // Loops through the Book objects in bookList to
            // print out what book was removed from the list
            foreach(Book item in bookList)
            {
                Console.WriteLine(item + " has been removed from the Book list.");
            }
            
            // Prevents the terminal from closing on start when running
            Console.ReadKey();
        }
         
    }
}
