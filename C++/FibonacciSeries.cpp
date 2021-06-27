#include <iostream>
#include <stdio.h>
using namespace std;

// Generates the fibonacci series
void generateFibonacciSeries(unsigned long long number)
{
    unsigned long long fib[number];
    fib[0] = 0;
    fib[1] = 1;

    for(int i = 2; i < number; i++)
        fib[i] = fib[i - 1] + fib[i - 2];

    cout << "\nThe Fibonnacci Sequence of " << number << ":" << endl;
    for(unsigned long long i = 0; i < number; i++)
        cout << fib[i] << " ";
    cout << "\n" << endl;
}

int main()
{
    unsigned long long number;
    cout << "Enter a number: ";
    cin >> number; 
    generateFibonacciSeries(number);
    system("pause");
}