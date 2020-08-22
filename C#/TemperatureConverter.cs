using System;

namespace TemperatureConverter
{
    class TemperatureConversion
    {
        public static void Main(string[] args)
        {
            Console.Write("What temperature unit would you like to convert (Farenheit/Celsius/Kelvin)? ");
            String unit = Console.ReadLine();

            Console.Write("To which unit? (Farenheit/Celsius/Kelvin)? ");
            String convert = Console.ReadLine();
            
            // Farenheit to Celsius
            if (unit == "Farenheit" && convert == "Celsius")
            {
                Console.Write("Enter a value: ");
                double val = Convert.ToDouble(Console.ReadLine());
                double c = ConvertFarenheitToCelsius(val);
                Console.WriteLine(c + " degrees Celsius");
            }
            // Farenheit to Kelvin
            if (unit == "Farenheit" && convert == "Kelvin")
            {
                Console.Write("Enter a value: ");
                double val = Convert.ToDouble(Console.ReadLine());
                double k = ConvertFarenheitToKelvin(val);
                Console.WriteLine(k + " degrees Kelvin");
            }
            // Celsius to Farenheit
            if (unit == "Celsius" && convert == "Farenheit")
            {
                Console.Write("Enter a value: ");
                double val = Convert.ToDouble(Console.ReadLine());
                double f = ConvertCelsiusToFarenheit(val);
                Console.WriteLine(f + " degrees Farenheit");
            }
            // Celsius to Kelvin
            if (unit == "Celsius" && convert == "Kelvin")
            {
                Console.Write("Enter a value: ");
                double val = Convert.ToDouble(Console.ReadLine());
                double k = ConvertCelsiusToKelvin(val);
                Console.WriteLine(k + " degrees Kelvin");
            }
            // Kelvin to Farenheit
            if (unit == "Kelvin" && convert == "Farenheit")
            {
                Console.Write("Enter a value: ");
                double val = Convert.ToDouble(Console.ReadLine());
                double f = ConvertKelvinToFarenheit(val);
                Console.WriteLine(f + " degrees Farenheit");
            }
            // Kelvin to Farenheit
            if (unit == "Kelvin" && convert == "Celsius")
            {
                Console.Write("Enter a value: ");
                double val = Convert.ToDouble(Console.ReadLine());
                double c = ConvertKelvinToCelsius(val);
                Console.WriteLine(c + " degrees Celsius");
            }

            if (unit == convert)
            {
                Console.WriteLine("Converting a unit of tempeature into a same unit of temperature (i.e. farenheit to farenheit) will not change the value.");
            }

            Console.ReadKey();
        }
    }
}
