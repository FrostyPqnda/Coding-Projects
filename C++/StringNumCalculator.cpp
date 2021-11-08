//
//  StringNumCalculator.cpp
//  StringNumCalculator
//
//  Created by Brian Pham on 6/1/20.
//  Copyright © 2020 FrostyPqnda. All rights reserved.
//

#include <stdio.h>
#include <iostream>
using namespace std;

static double AddStrings(string a, string b);
static double SubtractStrings(string a, string b);
static double MultiplyStrings(string a, string b);
static double DivideStrings(string a, string b);

int main()
{
    string x;
    cout << "Enter first value: ";
    cin >> x;

    string y;
    cout << "Enter second value: ";
    cin >> y;

    string input;
    cout << "[A]DD / [S]UBTRACT / [M]ULTIPLY / [D]IVIDE? ";
    cin >> input;

    if (input == "A")
    {
        double addS = AddStrings(x, y);
        cout << addS << endl;
    }
    else if (input == "S")
    {
        double subS = SubtractStrings(x, y);
        cout << subS << endl;
    }
    else if (input == "M")
    {
        double multS = MultiplyStrings(x, y);
        cout << multS << endl;
    }
    else if (input == "D")
    {
        double divS = DivideStrings(x, y);
        cout << divS << endl;
    }
}

// Converts two strings into a double and adds them
static double AddStrings(string a, string b)
{
    try {
        double sum;

        double aNum = stod(a);
        double bNum = stod(b);

        return sum = aNum + bNum;
    } catch (invalid_argument ia) {
        cout << ia.what();
    }

    return 0;
}

// Converts two strings into a double and subtracts them
static double SubtractStrings(string a, string b)
{
    try {
        double sum;

        double aNum = stod(a);
        double bNum = stod(b);

        return sum = aNum - bNum;
    } catch (invalid_argument ia) {
        cout << ia.what();
    }

    return 0;
}

// Converts two strings into a double and multiplies them
static double MultiplyStrings(string a, string b)
{
    try {
        double sum;

        double aNum = stod(a);
        double bNum = stod(b);

        return sum = aNum * bNum;
    } catch (invalid_argument ia) {
        cout << ia.what();
    }

    return 0;
}

// Converts two strings into a double and divides them
static double DivideStrings(string a, string b)
{
    try {
        double sum;

        double aNum = stod(a);
        double bNum = stod(b);
        if(bNum != 0)
        {
            return sum = aNum / bNum;
        }
        else
        {
            cout << "Cannot divide by 0 \n";
        }
    } catch (invalid_argument ia) {
        cout << ia.what();
    }

    return 0; // Returns 0 if value is a non-double or divided by 0
}
