using System;
using System.Collections.Generic;
using LinearSearchAlgorithm;

namespace LinearSearchAlgorithm
{
    class IndexSearch
    {
        static void Main(string[] args)
        {
            List<int> numbers = new List<int>();
            numbers.Add(15);
            numbers.Add(4);
            numbers.Add(53);
            numbers.Add(2);
            numbers.Add(133);

            LinearSearch search = new LinearSearch();

            int num = search.ListSearch(numbers, 54);


            Console.WriteLine(num);
            Console.ReadKey();
        }
    }
}
