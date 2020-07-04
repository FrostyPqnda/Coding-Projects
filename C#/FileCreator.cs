using System;
using System.IO;

/**
 * FileCreator is a simple program that allows
 * users to create their own files.
 */
namespace FileCreator
{
    class FileCreator
    {
        public static void Main(string[] args)
        {
            Console.Write("Name your file: ");
            string file = Console.ReadLine() + ".txt";
            Console.WriteLine(file);

            Console.Write("Write what you want in " + file + ": ");
            string fileContent = Console.ReadLine();

            File.WriteAllText(file, fileContent);

            Console.WriteLine(file + " has been created.");
        }
    }
}
