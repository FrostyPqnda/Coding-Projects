#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#define MAX 10000 // MAX size for 2D array

char puzzle[MAX][MAX], output[MAX][MAX], words[MAX][MAX];
int dir[8][2] = {
    {-1, -1},
    {-1, 0},
    {-1, 1},
    {0, -1},
    {0, 1},
    {1, -1},
    {1, 0},
    {1, 1}
};

void print(char board[MAX][MAX], int size) {
    for(int i = 0; i < size; i++) {
        for(int j = 0; j < size; j++) {
            printf("%c ", board[i][j]);
        }
        printf("\n");
    }
}

void scan(char* word, int row, int col, int size) {
    for(int i = 0; i < 8; i++) {
        int k, rowDir = row + dir[i][0], colDir = col + dir[i][1];
            
        for(k = 1; k < strlen(word); k++) {
            if(rowDir >= size || rowDir < 0 || colDir >= size || colDir < 0)
                break;

            if(puzzle[rowDir][colDir] != word[k])
                break;

            rowDir += dir[i][0];
            colDir += dir[i][1];
        }

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

void findWord(char* word, int size) {
    for(int i = 0; i < size; i++)
        for(int j = 0; j < size; j++) 
            if(puzzle[i][j] == word[0])
                scan(word, i, j, size);
}

int main(int argc, char *argv[]) {
    int boardSize, wordCount;

    if(argc != 2) {
        fprintf(stderr, "gcc WordPuzzle.c -o WordPuzzle\n");
        fprintf(stderr, "./WordPuzzle <input file>");
        exit(1);
    }

    FILE *fp = fopen(argv[1], "r");
    if(!fp) {
        fprintf(stderr, "%s cannot be opened for reading.\n", argv[1]);
        exit(1);
    }

    
    fscanf(fp, "%d", &boardSize);
    for(int i = 0; i < boardSize; i++) {
        for(int j = 0; j < boardSize; j++) {
            fscanf(fp, " %c", &puzzle[i][j]);
            output[i][j] = '-';
        }
    }

    print(puzzle, boardSize);

    fscanf(fp, "%d", &wordCount);
    for(int i = 0; i < wordCount; i++) {
        fscanf(fp, "%s\n", words[i]);
        printf("%s\n", words[i]);
        findWord(words[i], boardSize);
    }

    if(fclose(fp) != 0) {
        fprintf(stderr, "Failed to close %s\n", argv[1]);
        exit(1);
    }

    print(output, boardSize);
}