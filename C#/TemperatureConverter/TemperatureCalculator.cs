using System;

namespace TemperatureConverter
{
    class TemperatureCalculator
    {
        // ConvertFarenheitToCelsius converts the given farenheit temperature into
        // celsius
        public static double ConvertFarenheitToCelsius(double farenheit)
        {
            double celsius = ((farenheit - 32) * 5) / 9;
            return Math.Round(celsius, 2);
        }

        // ConvertCelsiusToFarenheit converts the given celsius temperature into
        // farenheit
        public static double ConvertCelsiusToFarenheit(double celsius)
        {
            double farenheit = (celsius * 9 / 5) + 32;
            return Math.Round(farenheit, 2);

        }

        // ConvertFarenheitToKelvin converts the given farenheit temperature into
        // kelvin
        public static double ConvertFarenheitToKelvin(double farenheit)
        {
            double kelvin = (farenheit - 32) * 5 / 9 + 273.15;
            return Math.Round(kelvin, 2);
        }

        // ConvertCelsiusToKelvin converts the given celsius temperature into
        // kelvin
        public static double ConvertCelsiusToKelvin(double celsius)
        {
            double kelvin = celsius + 273.15;
            return Math.Round(kelvin, 2);
        }

        // ConvertKelvinToFarenheit converts the given kelvin temperature into
        // farenheit
        public static double ConvertKelvinToFarenheit(double kelvin)
        {
            double farenheit = (kelvin - 273.15) * 9 / 5 + 32;
            return Math.Round(farenheit, 2);
        }

        // ConvertKelvinToCelsius converts the given kelvin temperature into
        // celsius
        public static double ConvertKelvinToCelsius(double kelvin)
        {
            double celsius = kelvin - 273.15;
            return Math.Round(celsius, 2);
        }
    }
}