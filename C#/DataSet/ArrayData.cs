using System;

namespace Data
{
    class ArrayData
    {
        static void Main(string[] args)
        {
            Console.Title = "Data Set";
            Console.ForegroundColor = ConsoleColor.DarkCyan;

            DataSet data = new DataSet(5);
            Console.WriteLine("Generated data: " + data);
            
            Console.ReadKey();
        }
    }
}