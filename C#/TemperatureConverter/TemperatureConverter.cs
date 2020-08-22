using System;

namespace TemperatureConverter
{
    class TemperatureConversion : TemperatureCalculator
    {
        static void Main(string[] args)
        {   
            Console.Write("What temperature unit would you like to convert (Farenheit (F)/ Celsius (C)/ Kelvin (K))? ");
            String unit = Console.ReadLine();

            Console.Write("To which unit? (Farenheit (F)/ Celsius (C)/ Kelvin (K))? ");
            String convert = Console.ReadLine();
            
            // Farenheit to Celsius
            if (unit.Equals("F", StringComparison.OrdinalIgnoreCase) && convert.Equals("C", StringComparison.OrdinalIgnoreCase))
            {
                Console.Write("Enter a value: ");
                double val = Convert.ToDouble(Console.ReadLine());
                double c = ConvertFarenheitToCelsius(val);
                Console.WriteLine(c + " degrees Celsius");
            }
            // Farenheit to Kelvin
            if (unit.Equals("F", StringComparison.OrdinalIgnoreCase) && convert.Equals("K", StringComparison.OrdinalIgnoreCase))
            {
                Console.Write("Enter a value: ");
                double val = Convert.ToDouble(Console.ReadLine());
                double k = ConvertFarenheitToKelvin(val);
                Console.WriteLine(k + " degrees Kelvin");
            }
            // Celsius to Farenheit
            if (unit.Equals("C", StringComparison.OrdinalIgnoreCase) && convert.Equals("F", StringComparison.OrdinalIgnoreCase))
            {
                Console.Write("Enter a value: ");
                double val = Convert.ToDouble(Console.ReadLine());
                double f = ConvertCelsiusToFarenheit(val);
                Console.WriteLine(f + " degrees Farenheit");
            }
            // Celsius to Kelvin
            if (unit.Equals("C", StringComparison.OrdinalIgnoreCase) && convert.Equals("K", StringComparison.OrdinalIgnoreCase))
            {
                Console.Write("Enter a value: ");
                double val = Convert.ToDouble(Console.ReadLine());
                double k = ConvertCelsiusToKelvin(val);
                Console.WriteLine(k + " degrees Kelvin");
            }
            // Kelvin to Farenheit
            if (unit.Equals("Kelvin", StringComparison.OrdinalIgnoreCase) && convert.Equals("F", StringComparison.OrdinalIgnoreCase))
            {
                Console.Write("Enter a value: ");
                double val = Convert.ToDouble(Console.ReadLine());
                double f = ConvertKelvinToFarenheit(val);
                Console.WriteLine(f + " degrees Farenheit");
            }
            // Kelvin to Farenheit
            if (unit.Equals("Kelvin", StringComparison.OrdinalIgnoreCase) && convert.Equals("C", StringComparison.OrdinalIgnoreCase))
            {
                Console.Write("Enter a value: ");
                double val = Convert.ToDouble(Console.ReadLine());
                double c = ConvertKelvinToCelsius(val);
                Console.WriteLine(c + " degrees Celsius");
            }

            if(unit.Equals(convert, StringComparison.OrdinalIgnoreCase))
            {
                Console.WriteLine("Cannot convert " + unit + " to " + convert + ". Must be of different unit.");
            }

            Console.ReadKey();
        }

        
    }
}
