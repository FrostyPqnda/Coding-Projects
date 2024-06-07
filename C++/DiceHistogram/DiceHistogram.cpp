#include <iostream>
#include <random>
#include <fstream>
using namespace std;

int* generate(); // Generate an array of dice rolls
int max(int* diceArr, int left, int right); // Find the max in the dice rolls
int* sort(int* diceArr); // Sort the dice rolls
int* bucketize(int* diceArr); // Get the frequency of the dice rolls
void normalize(int* bucket); // Normalize the frequency of the dice rolls
void write(string outFile, const int* bucket); // Write to an output file
void error(int argc, char* argv[]); // Output error messages

// The no. of die and no. of rolls, respectively
int numDice, numRoll;

// C++ program that generates a histogram of dice rolls
int main(int argc, char* argv[]) {
    error(argc, argv);

    numDice = atoi(argv[1]), numRoll = atoi(argv[2]);
    int* rolls = generate();
    int* bucket = bucketize(rolls);
    normalize(bucket);
    
    write("output.txt", bucket);

    delete[] rolls;
    delete[] bucket;
    return 0;
}

/**
 * @return An array of dice rolls
 */
int* generate() {
    int* diceArr = new int[numRoll];
    random_device rd;
    default_random_engine generator(rd()); 
    uniform_int_distribution<int> distribution(1, 6);

    for(int i = 0; i < numRoll; i++) {
        int diceValue = 0;
        for(int j = 0; j < numDice; j++)
            diceValue += distribution(generator);

        diceArr[i] = diceValue;
    }

    return sort(diceArr);
}

/**
 * @param diceArr The array to be searched
 * @param left The leftmost index of the array
 * @param right The rightmost index of the array
 * @return The maximum value in the array
 */
int max(int* diceArr, int left, int right) {
    if(left >= right) return diceArr[left];
    int mid = (left + right) / 2;
    int leftMax = max(diceArr, left, mid);
    int rightMax = max(diceArr, mid + 1, right);
    return max(leftMax, rightMax);
}

/**
 * @param diceArr The array to be sorted using Counting Sort
 * @return The sorted array
 */
int* sort(int* diceArr) {
    const int M = max(diceArr, 0, numRoll - 1);
    int freq[M + 1] = {0};

    for(int i = 0; i < numRoll; i++)
        freq[diceArr[i]]++;
    
    for(int i = 1; i <= M; i++)
        freq[i] += freq[i - 1];

    int* sortedDice = new int[numRoll];
    for(int i = numRoll - 1; i >= 0; i--) {
        sortedDice[freq[diceArr[i]] - 1] = diceArr[i];
        freq[diceArr[i]]--;
    }

    return sortedDice;
}

/**
 * @param diceArr The input array
 * @return A frequency array of each item in the array
 */
int* bucketize(int* diceArr) {
    int* freq = new int[(6 * numDice) + 1]{0};

    for(int i = 0; i < numRoll; i++)
        freq[diceArr[i]]++;
    
    return freq;
}

/**
 * @param bucket The frequency array that will be normalized
 */
void normalize(int* bucket) {
    int maxVal = max(bucket, 0, (6 * numDice));

    for(int i = 1 * numDice; i <= (6 * numDice); i++) {
        // Normalizing the bucket may cause it to be set to 0.
        // If so, set the normalized value to 1
        int normValue = (bucket[i] * 10) / maxVal;
        bucket[i] = (normValue > 1) ? normValue : 1;
    }
}

/**
 * @param outFile The output file
 * @param bucket The bucket data that will be written to the output file
 */
void write(string outFile, const int* bucket) {
    ofstream outStream;
    outStream.open(outFile);

    outStream << "No. of Die: " << numDice << endl;
    outStream << "No. of Rolls: " << numRoll << endl;
    outStream << endl;

    for(int i = 1 * numDice; i <= 6 * numDice; i++) {
        string s = "";
        for(int j = 0; j < bucket[i]; j++) 
            s += "*";
        outStream << i << ": "  << s << endl;
    }

    outStream.close();
}

void error(int argc, char* argv[]) {
    if(argc != 3) {
        cerr << "g++ -o <Output Exe> DiceHistogram.cpp" << endl;
        cerr << "./<Output Exe> <No. of Die> <No. of Rolls>" << endl;
        exit(1);
    } 

    if(atoi(argv[1]) <= 0 || atoi(argv[2]) <= 0) {
        if(atoi(argv[1]) <= 0)
            cerr << "No. of die cannot be less than 1!" << endl;

        if(atoi(argv[2]) <= 0)
            cerr << "No. of rolls cannot be less than 1!" << endl;

        exit(1);
    }    
}   