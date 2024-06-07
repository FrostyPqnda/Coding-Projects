#ifndef SPELLCHECK_H_INCLUDED
#define SPELLCHECK_H_INCLUDED 

#define MAXWORD 1000000
#define MAXLEN 1000
#define MIN(a, b, c) (a < b && a < c) ? a : (b < a && b < c) ? b : c

typedef struct Dictionary {
    char** dictionary;
    int length;
    int* edits;
} Dictionary;

Dictionary readDictionary(char* file);
Dictionary possibleWords(char* word);

// Algorithms to be implemented in the Algrothm.c file
void merge(Dictionary dict, int left, int mid, int right, int hasEdits);
void mergeSort(Dictionary dict, int left, int right, int hasEdits);
void sort(Dictionary dict, int hasEdits);
int binarySearch(Dictionary dict, char* word, int left, int right);
int search(Dictionary dict, char* word);
int editDistance(char* word1, char* word2);

#endif