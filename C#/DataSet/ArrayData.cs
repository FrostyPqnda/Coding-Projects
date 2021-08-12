using System;

namespace Data
{
    class ArrayData
    {
        static void Main(string[] args)
        {
            Console.Title = "Data Set";
            Console.ForegroundColor = ConsoleColor.DarkCyan;

            int[] arr = {1, 2, 3, 2, 5, 7};
            DataSet data = new DataSet(arr);
            Console.WriteLine(data);
            Console.ReadKey();
        }
    }
}