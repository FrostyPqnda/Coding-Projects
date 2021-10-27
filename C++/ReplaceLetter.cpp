//
//  ReplaceLetter.cpp
//  ReplaceLetter
//
//  Created by Brian Pham on 6/1/20.
//  Copyright © 2020 FrostyPqnda. All rights reserved.
#include <iostream>
#include <stdio.h>
using namespace std;

// Replaces all instances of a letter with a new letter
static string ReplaceLetter(string word, char removeLetter, char replacingLetter)
{
    string val = "";
    
    for(int i = 0; i < word.length(); i++)
        if(word.at(i) == removeLetter)
            val += replacingLetter;
        else
            val += word.at(i);
    return val;
}

int main()
{
    string input;
    cout << "Enter a word: ";
    getline(cin, input);
    
    char remove;
    cout << "Enter the character you want to remove: ";
    cin >> remove;
    
    char replace;
    cout << "Enter the new character you want to add: ";
    cin >> replace;
    
    string result = ReplaceLetter(input, remove, replace);
    cout << result << endl;
}
