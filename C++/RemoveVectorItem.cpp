/**
 * Author: Brian Pham / FrostyPqnda
 * Date: 10/26/2021
 * 
 * Purpose of the program: Remove an item from a vector without
 * using the built-in vector.erase() method.
 */
#include <iostream>
#include <vector>
using namespace std;

int search(vector<int>&, int);
void shiftElement(vector<int>&, int);
void removeItem(vector<int>&, int);

int main() {
    vector<int> data = {3, 7, 8, 9, 10};

    cout << "OLD DATA: ";
    
    for(int item : data) {
        cout << item << " ";
    }

    cout << "\n\nNEW DATA: "; 
    removeItem(data, 8);
    for(int item : data) {
        cout << item << " ";
    }

    return 0;
}

// Searches for the index position of the item in the data
// Return the position if found. Otherwise, return -1.
int search(vector<int>& data, int item) {
    for(int i = 0; i < data.size(); i++) {
        if(data[i] == item) {
            return i;
        }
    }

    return -1;
}

// Shifts the elements of the data starting from [startPos] to the left
// [startPos] is the index position of the item in the data
void shiftElement(vector<int>& data, int startPos) {
    int temp = data[startPos];

    for(int i = startPos; i < data.size(); i++) {
        data[i] = data[i + 1];
    }

    data[data.size() - 1] = temp;

    return;
}

// Removes an item from the vector without using the built-in
// vector.erase() method.
void removeItem(vector<int>& data, int item) {
    int pos = search(data, item);

    // Only remove the item, if item's position is valid
    if(pos != -1) {
        shiftElement(data, pos);
        data.pop_back();
    }

    return;
}