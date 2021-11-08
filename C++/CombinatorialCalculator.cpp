#include <iostream>
#include <stdio.h>
using namespace std;

static unsigned long long Factorial(unsigned long long n);
static unsigned long long Combination(unsigned long long a, unsigned long long b);

int main() 
{
    unsigned long long x;
    cout << "Enter an integer: ";
    cin >> x;
    
    unsigned long long y;
    cout << "Enter an integer: ";
    cin >> y; 

    cout << "\nTotal number of objects in the set: " << max(x, y) << endl << "Number of sampling objects in the set: " << min(x, y) << endl << "The total number of combination is " << Combination(x,y) << endl;
    return 0;
}

// Calculates the Factorial of a given number.
// n * (n - 1)
static unsigned long long Factorial(unsigned long long n) 
{
    // Base case 
    if(n == 0)
    {
        return 1;
    }
    // Recursive call
    return n * Factorial(n - 1);
}

// Calculates the combination from a sampling size in a set of objects.
static unsigned long long Combination(unsigned long long a, unsigned long long b)
{
    return Factorial(max(a, b)) / (Factorial(min(a, b)) * Factorial(max(a, b) - min(a, b)));
}
