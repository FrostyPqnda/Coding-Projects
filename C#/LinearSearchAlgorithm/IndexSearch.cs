using System;
using System.Collections.Generic;
using LinearSearchAlgorithm;

namespace LinearSearchAlgorithm
{
    class IndexSearch
    {
        static void Main(string[] args)
        {
            String[] str = {
                "Dogs", "Cats", "Turtles",
                "Fish", "Snakes"
            };

            LinearSearch search = new LinearSearch();
            int index = search.ListSearch(str, "Dogs");

            Console.WriteLine(index);
            Console.ReadKey();
        }
    }
}
