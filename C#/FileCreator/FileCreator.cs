using System;
using System.IO;

namespace FileCreator
{
    class FileWriter
    {
        static void Main(string[] args)
        {
            Console.Write("Name your file: ");
            string file = Console.ReadLine() + ".txt";
            Console.WriteLine(file);

            Console.Write("Write what you want in " + file + ": ");
            string fileContent = Console.ReadLine();

            File.WriteAllText(file, fileContent);

            Console.WriteLine(file + " has been created.");

            Console.ReadKey();
        }
    }
}
