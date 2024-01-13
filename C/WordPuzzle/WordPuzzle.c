#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#define MAX_LEN 1000 // MAX string size

int boardSize, wordCount; // size of the puzzle, number of words in the puzzle
char **puzzle, **output, **words; // 2D char pointers

// The direction to which the words will be searched from
int dir[8][2] = {
    {-1, -1},   // NW
    {-1, 0},    // N
    {-1, 1},    // NE
    {0, -1},    // W
    {0, 1},     // E
    {1, -1},    // SW
    {1, 0},     // S
    {1, 1}      // SE
};

// Print the board
void print(char** board) {
    for(int i = 0; i < boardSize; i++) {
        for(int j = 0; j < boardSize; j++) {
            printf("%c ", board[i][j]);
        }
        printf("\n");
    }
}

// Scan for a word in the puzzle in a given (row, col) position
void scan(char* word, int row, int col) {
    for(int i = 0; i < 8; i++) {

        int k, rowDir = row + dir[i][0], colDir = col + dir[i][1];
            
        for(k = 1; k < strlen(word); k++) {
            if(rowDir >= boardSize || rowDir < 0 || colDir >= boardSize || colDir < 0)
                break;

            // If the character in the puzzle is not a chaacter in the word, then
            // end the loop
            if(puzzle[rowDir][colDir] != word[k])
                break;

            // Increment rowDir, colDir to search in the next direction
            rowDir += dir[i][0];
            colDir += dir[i][1];
        }
        
        // If a word has been found in the puzzle, copy
        // the word's location onto the output array
        if(k == strlen(word)) {
            rowDir = row, colDir = col;
 
            for(int j = 0; j < k; j++) {
                output[rowDir][colDir] = word[j];
                rowDir += dir[i][0];
                colDir += dir[i][1];
            }
        }
    }
}

// Find the location of a word in the puzzle
void findWord(char* word) {
    for(int i = 0; i < boardSize; i++)
        for(int j = 0; j < boardSize; j++) 
            if(puzzle[i][j] == word[0])
                scan(word, i, j);
}

// Load the content of a file
int loadFile(char* file) {
    // Attempt to open file for reading. Output an error if file cannot be read
    FILE *fp = fopen(file, "r");
    if(!fp) {
        fprintf(stderr, "%s cannot be opened for reading.\n", file);
        exit(0);
    }

    // Read the size of the puzzle
    fscanf(fp, "%d", &boardSize);   

    // Dynamically allocate two 2D char array using the board size
    puzzle = (char**)malloc(boardSize * sizeof(char*));
    output = (char**)malloc(boardSize * sizeof(char*));

    // Dynamically allocate a char array for each pointer in the puzzle and output arrays
    for(int i = 0; i < boardSize; i++) {
        puzzle[i] = (char*)malloc(boardSize * sizeof(char));
        output[i] = (char*)malloc(boardSize * sizeof(char));
    }

    // Scan the puzzle content into the puzzle array
    for(int i = 0; i < boardSize; i++) {
        for(int j = 0; j < boardSize; j++) {
            fscanf(fp, " %c", &puzzle[i][j]);
            output[i][j] = '-';
        }
    }

    // Read the word count in the file
    fscanf(fp, "%d", &wordCount);

    // Dynamically allocate a 2D char array to store words into an array
    words = (char**)malloc(wordCount * sizeof(char));
    for(int i = 0; i < wordCount; i++)
        words[i] = malloc(MAX_LEN * sizeof(char));
    
    // Read the words content into the words array
    for(int i = 0; i < wordCount; i++) {
        fscanf(fp, "%s", words[i]);
    }

    // Attempt to close file. Output an error if file cannot be closed
    if(fclose(fp)) {
        fprintf(stderr, "Failed to close %s\n", file);
        exit(0);
    }

    return 1; // Return 1 to signify successful loading
}

int main(int argc, char *argv[]) {
    if(argc != 2) {
        fprintf(stderr, "gcc WordPuzzle.c -o WordPuzzle\n");
        fprintf(stderr, "./WordPuzzle <input file>");
        exit(1);
    }

    loadFile(argv[1]);
    print(puzzle);

    for(int i = 0; i < wordCount; i++) {
        printf("%s\n", words[i]);
        findWord(words[i]);
    }

    print(output);

    // Free the dynamically allocated arrays
    for(int i = 0; i < boardSize; i++) {
        free(puzzle[i]);
        free(output[i]);
    }

    for(int i = 0; i < wordCount; i++) {
        free(words[i]);
    }

    free(puzzle);
    free(output);
    free(words);

    return 0;
}