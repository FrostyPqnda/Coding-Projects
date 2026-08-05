#include <iostream>
#include <fstream>
using namespace std;

void readFile(string inputFile, string outputFile);
string reverse(string s);


int main(int argc, char* argv[]) {
    if (argc != 3) {
        cerr << "Incorrect no. of arguments" << endl;
        cerr << "Expected: ./" << argv[0] << " <input file> <output file>" << endl;
        return -1;
    }

    readFile(argv[1], argv[2]);
    return 0;
}

// Read in a text file and write the reversed line to an output file
void readFile(string inputFile, string outputFile) {
    // Open the input file in the input stream for reading
    ifstream inFile(inputFile);

    if(!inFile) {
        cerr << "Failed to open input file " << inputFile << endl;
        return;
    }
    
    // Open the output file in the output stream for writing
    ofstream outFile(outputFile);

    if(!outFile) {
        cerr << "Failed to open output file " << outputFile << endl;
        return;
    }

    // Used to store the line
    string txt; 

    // Read each line from the input file into txt
    while (getline(inFile, txt)) {
        // Write the reverse of the line 
        outFile << reverse(txt) << endl;
    }

    // Close the input and output streams
    inFile.close();
    outFile.close();

    cout << "Output written to " << outputFile << endl;
}

// Reverse a string
string reverse(string s) {
    int l = 0;
    int r = s.length() - 1;

    while (l < r) {
        swap(s[l], s[r]);
        l++;
        r--;
    }

    return s;
}