using System;

namespace Data
{
    class ArrayData
    {
        static void Main(string[] args)
        {
            Console.Title = "Data Set";
            Console.ForegroundColor = ConsoleColor.DarkCyan;

            DataSet data = new DataSet(20);
            Console.WriteLine(data);
            Console.ReadKey();
        }
    }
}