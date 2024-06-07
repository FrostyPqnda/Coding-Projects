#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "SpellCheck.h"

/**
 * Merge the left dictionary and right dictionary into one dictionary
 * 
 * @param dict The dictionary to be merged
 * @param left The leftmost index of the dictionary
 * @param mid The middle index of the dictionary
 * @param right The rightmost index of the dictionary
 * @param hasEdits The dictionary is maintaining a list of edit distances
 */
void merge(Dictionary dict, int left, int mid, int right, int hasEdits) {
    int size = right - left + 1;
    char** tmpDicts = (char**)malloc(size * sizeof(char*));
    for(int i = 0; i < size; i++) 
        tmpDicts[i] = (char*)malloc(MAXLEN * sizeof(char));
    int* tmpEdits = (int*)malloc(size * sizeof(int*));

    int i = left, j = mid + 1, k = 0;

    for(i = left, j = mid + 1, k = 0; i <= mid && j <= right; k++) {
        if(hasEdits) {
            if(dict.edits[i] <= dict.edits[j]) {
                tmpDicts[k] = dict.dictionary[i];
                tmpEdits[k] = dict.edits[i];
                i++;
            } else {
                tmpDicts[k] = dict.dictionary[j];
                tmpEdits[k] = dict.edits[j];
                j++;
            }
        } else {
            if(strcasecmp(dict.dictionary[i], dict.dictionary[j]) < 0) {
                tmpDicts[k] = dict.dictionary[i++];
            } else {
                tmpDicts[k] = dict.dictionary[j++];
            }
        }   
    }

    for(; i <= mid; i++, k++) {
        tmpDicts[k] = dict.dictionary[i];
        if(hasEdits) {tmpEdits[k] = dict.edits[i];}
    }

    for(; j <= right; j++, k++) {
        tmpDicts[k] = dict.dictionary[j];
        if(hasEdits) {tmpEdits[k] = dict.edits[j];}
    }    

    for(i = left; i <= right; i++) {
        dict.dictionary[i] = tmpDicts[i - left];
        if(hasEdits) {dict.edits[i] = tmpEdits[i - left];} 
    }

    free(tmpEdits);
}

/**
 * Sort the dictionary using Merge Sort - A divide
 * and conquer algorithm.
 * 
 * @param Dictionary The dictionary to be merged
 * @param left The leftmost index of the dictionary
 * @param right The rightmost index of the dictionary
 * @param hasEdits The dictionary is maintaining a list of edit distances
 */
void mergeSort(Dictionary dict, int left, int right, int hasEdits) {
    if(left >= right) return;

    int mid = (left + right) / 2;
    mergeSort(dict, left, mid, hasEdits);
    mergeSort(dict, mid + 1, right, hasEdits);
    merge(dict, left, mid, right, hasEdits);
}

/**
 * Sort the dictionary using the Merge Sort algorithm.
 * 
 * @param dict Dictionary to be sorted
 * @param hasEdits Dictionary is maintaining a list of edit distance
 */
void sort(Dictionary dict, int hasEdits) {
    mergeSort(dict, 0, dict.length - 1, hasEdits);
}

/**
 * Search for a word in the dictionary using the Binary Search
 * algorithm.
 * 
 * @param dict A dictionary of words
 * @param word The word that will be searched in the dictionary
 * @param left The leftmost index of the dictionary
 * @param right The rightmost index of the dictionary
 * 
 * @return The index position of word in the dictionary. Otherwise, -1
 */
int binarySearch(Dictionary dict, char* word, int left, int right) {
    if(left > right) return -1;

    int mid = left + (right - left) / 2;
    int cmp = strcasecmp(dict.dictionary[mid], word);

    if(cmp > 0) return binarySearch(dict, word, left, mid - 1);
    else if(cmp < 0) return binarySearch(dict, word, mid + 1, right);
    else return mid;
}

/**
 * Search for a word in the dictionary
 * 
 * @param dict A dictionary of words
 * @param word The word that will be searched in the dictionary
 * 
 * @return The index position of word in the dictionary. Otherwise, -1
 */
int search(Dictionary dict, char* word) {
    return binarySearch(dict, word, 0, dict.length - 1);
}

/**
 * Find the minimum edit distance between 
 * two strings.
 * 
 * @param word1 The first word
 * @param word2 The second word
 * 
 * @return The minimum edit distance between the two words
 */
int editDistance(char* word1, char* word2) {
    int m = strlen(word1), n = strlen(word2);

    int** edit = (int**)malloc((m + 1) * sizeof(int *));
    for(int i = 0; i <= m; i++)
        edit[i] = (int*)malloc((n + 1) * sizeof(int));

    for(int i = 0; i <= m; i++) 
        for(int j = 0; j <= n; j++) 
            edit[i][j] = 0;

    for(int j = 1; j <= n; j++)
        edit[0][j] = j;

    for(int i = 1; i <= m; i++) {
        edit[i][0] = i;
        for(int j = 1; j <= n; j++) {
            int ins = edit[i][j - 1] + 1;
            int del = edit[i - 1][j] + 1;
            int rep = (word1[i - 1] == word2[j - 1]) ? edit[i - 1][j - 1] : edit[i - 1][j - 1] + 1;
            edit[i][j] = MIN(ins, del, rep);
        }
    }

    int minEdit = edit[m][n];
    
    for(int i = 0; i <= m; i++)
        free(edit[i]);
    free(edit);

    return minEdit;
}

