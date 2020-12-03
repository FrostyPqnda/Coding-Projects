#include <iostream>
#include <stdio.h>
using namespace std;

// Calculates the factorial of a given number.
// n * (n - 1)
static unsigned long long factorial(unsigned long long n) 
{
    // Base case 
    if(n == 0)
        return 1;
    // Recursive call
    return n * factorial(n - 1);
}

// Calculates the combination from a sampling size in a set of objects.
static unsigned long long combination(unsigned long long a, unsigned long long b)
{
    return factorial(max(a, b)) / (factorial(min(a, b)) * factorial(max(a, b) - min(a, b)));
}

int main() 
{
    unsigned long long x;
    cout << "Enter an integer: ";
    cin >> x;
    
    unsigned long long y;
    cout << "Enter an integer: ";
    cin >> y; 

    cout << "\nTotal number of objects in the set: " << max(x, y) << endl << "Number of sampling objects in the set: " << min(x, y) << endl << "The total number of combination is " << combination(x,y) << endl;
}