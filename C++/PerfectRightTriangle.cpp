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
    cout << isPythagoreanTriple(3, 4, 5) << endl;
    return 0;
}