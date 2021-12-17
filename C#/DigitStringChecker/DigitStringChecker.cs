using System;

namespace DigitStringChecker
{
    class DigitInString
    {
        static void Main(string[] args)
        {
            Console.Write("Enter a text: ");
            bool isDigit = DigitChecker(Console.ReadLine());

            if(isDigit) 
                Console.WriteLine("Contains a digit!");
            else
                Console.WriteLine("Does not contain a digit");

            Console.ReadKey();
        }

        // DigitChecker loops through @param str for any
        // possible numbers
        static bool DigitChecker(String str)
        {
            char[] digit = str.ToCharArray();
            bool isDigit = false;

            if(str != null) 
            {
                foreach (char c in digit)
                {
                    if(isDigit = Char.IsDigit(c))
                    {
                        break;
                    }
                }
            }

            return isDigit;
        }
    }
}
