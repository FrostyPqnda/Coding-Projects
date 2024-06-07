#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "SpellCheck.h"
#include "Algorithm.c"

/**
 * A C program that finds all possible words similar to 
 * the user's word if it is misspelled.
 */
int main(int argc, char* argv[]) {
    if(argc != 2) {
        fprintf(stderr, "gcc SpellCheck.c -o SpellCheck\n");
        fprintf(stderr, "./SpellCheck <Word>\n");
        exit(1);
    }

    Dictionary pw = possibleWords(argv[1]);
    printf("Top %d suggestions for \"%s\"\n", pw.length, argv[1]);
    for(int i = 0; i < pw.length; i++) {
        printf("%s (Distance: %d)\n", pw.dictionary[i], pw.edits[i]);
    }

    return 0;
} 

/**
 * Load a list of words from a text file into
 * a Dictionary struct
 * 
 * @param file A text file to be read into the dictionary
 * 
 * @return A Dictionary containing all words from the text file
 */
Dictionary readDictionary(char* file) {
    FILE* fp = fopen(file, "r");
    if(!fp) {
        fprintf(stderr, "%s could not be opened for reading\n", file);
        exit(1);
    }

    Dictionary dict;

    dict.dictionary = (char**)malloc(MAXWORD * sizeof(char*));
    for(int i = 0; i < MAXWORD; i++)
        dict.dictionary[i] = (char*)malloc(MAXLEN * sizeof(char));

    dict.length = -1;
    while(fscanf(fp, "%s", dict.dictionary[++dict.length]) == 1);
    fclose(fp);

    sort(dict, 0);
    return dict;
}

/**
 * Find all possible words related the given word.
 * 
 * @param word The word to be used to generate all possible words similar to it.
 * 
 * @return A dictionary of similar words
 */
Dictionary possibleWords(char* word) {
    Dictionary dict = readDictionary("dictionary.txt");
    int idx = search(dict, word);
    if(idx != -1) 
        exit(1);

    dict.edits = (int*)malloc(MAXWORD * sizeof(int));
    for(int i = 0; i < dict.length; i++)
        dict.edits[i] = editDistance(word, dict.dictionary[i]);

    sort(dict, 1);

    int minEdit = -1;
    while(dict.edits[++minEdit] != 3);
    dict.length = minEdit;

    return dict;
}