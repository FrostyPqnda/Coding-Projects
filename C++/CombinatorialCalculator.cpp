#include <iostream>
#include <stdio.h>
using namespace std;

static unsigned long long factorial(unsigned long long n) 
{
    if(n == 0)
        return 1;
    else
        return n * factorial(n - 1);
}

static unsigned long long combination(unsigned long long total, unsigned long long sample)
{
    return factorial(total) / (factorial(sample) * factorial(total - sample));
}

int main() 
{
    unsigned long long x;
    cout << "Enter the total number of objects in the set: ";
    cin >> x;

    unsigned long long y;
    cout << "Enter the number of sampling objects from the set: ";
    cin >> y;

    cout << "The total number of combination is " << combination(x,y) << endl;
}