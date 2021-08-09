#include <iostream>
#include <stdio.h>
#include <math.h>
using namespace std;

bool isPythagoreanTriple(int a, int b, int c)
{
    int squaredC = pow(a, 2) + pow(b, 2);
    int sqrtC = sqrt(squaredC);

    if(sqrtC == c)
    {
        cout << boolalpha;
        return true;
    }
    else
    {
        cout << boolalpha;
        return false;
    }
}

int main()
{
    int a;
    cout << "Enter the 1st number: ";
    cin >> a;

    int b;
    cout << "Enter the 2nd number: ";
    cin >> b;

    int c;
    cout << "Enter the 3rd number: ";
    cin >> c;

    cout << "Is the set [" << a << ", " << b << ", " << c << "] a Pythagorean Triple? " << isPythagoreanTriple(a, b, c) << endl;
    return 0;
}