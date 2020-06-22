using System;

namespace ConvertToDouble
{
    class StringConversion
    {
        public static void Main(string[] args)
        {
            Console.Write("Enter a value: ");
            double val = Convert.ToDouble(Console.ReadLine());
            Console.WriteLine("Result: " + val);
        }

        static double convertToInt(string val)
        {
            try
            {
                double num = Convert.ToDouble(val);
                return num;
            }
            catch(FormatException x)
            {
                Console.WriteLine(x.Message);
            }

            return 0;
        }
    }
}
