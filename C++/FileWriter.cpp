//
//  FileWriter.cpp
//  FileWriter
//
//  Created by Brian Pham on 6/1/20.
//  Copyright © 2020 FrostyPqnda. All rights reserved.
//

#include <stdio.h>
#include <iostream>
#include <fstream>
using namespace std;

int main()
{
    // Create file
    string fileName;
    cout << "Name your file: ";
    cin >> fileName;
    
    string file = fileName.append(".txt");
    
    // Write file content
    string content;
    cout << "Enter text: ";
    cin >> content;
    
    ofstream user_file;
    user_file.open(file, ios::out);
    
    if(!user_file)
    {
        cout << "Failed to create file \n";
    }
    else
    {
        cout << file << " has been created \n";
        user_file << content;
        user_file.close();
    }
}
