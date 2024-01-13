#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int **puzzle, size;
char **output;

void PrintBoard(int** matrix) {
    for(int i = 0; i < size; i++) {
        for(int j = 0; j < size; j++) {
            printf("%d ", matrix[i][j]);
        }
        printf("\n");
    }
}

void PrintOutput(char** matrix) {
    for(int i = 0; i < size; i++) {
        for(int j = 0; j < size; j++) {
            printf("%c ", matrix[i][j]);
        }
        printf("\n");
    }
}

int LoadFile(char* file) {
    FILE *fp = fopen(file, "r");
    if(!fp) {
        fprintf(stderr, "%s could not be opened for reading\n", file);
        exit(0);
    }

    fscanf(fp, "%d", &size);
    puzzle = (int**)malloc(size * sizeof(int*));
    output = (char**)malloc(size * sizeof(char*));

    for(int i = 0; i < size; i++) {
        puzzle[i] = (int*)malloc(size * sizeof(int));
        output[i] = (char*)malloc(size * sizeof(char));
    }

    for(int i = 0; i < size; i++) {
        for(int j = 0; j < size; j++) {
            fscanf(fp, " %d", &puzzle[i][j]);
            output[i][j] = '-';
        }
    }
        
    if(fclose(fp)) {
        fprintf(stderr, "%s could not be closed\n", file);
        exit(0);
    }
    
    return 1;
}

int CheckRow(int rowPos) {
    for(int i = 0; i < size; i++)
        for(int j = 0; j < size; j++) 
            output[rowPos - 1][i] = puzzle[rowPos - 1][i] + '0';
    
    for(int i = 0; i < size; i++) 
        for(int j = i + 1; j < size; j++)
            if(puzzle[rowPos - 1][i] == puzzle[rowPos - 1][j])
                return 0;

    return 1;
}

int CheckColumn(int colPos) {
    for(int i = 0; i < size; i++)
        for(int j = 0; j < size; j++) 
            output[i][colPos - 1] = puzzle[i][colPos - 1] + '0';

    for(int i = 0; i < size; i++)
        for(int j = i + 1; j < size; j++) 
            if(puzzle[i][colPos - 1] == puzzle[j][colPos - 1]) 
                return 0;

    return 1;
}

int CheckBox(int boxNum) {
    int* box = (int*)malloc(size * sizeof(int));
    int k = 0;

    int iMin, iMax;
    if(boxNum >= 1 && boxNum <= 3) {
        iMin = 0, iMax = 3;
    } else if(boxNum >= 4 && boxNum <= 6) {
        iMin = 3, iMax = 6;
    } else if(boxNum >= 7 && boxNum <= 9) {
        iMin = 6, iMax = 9;
    }   

    int jMin, jMax;
    if(boxNum % 3 == 1) {
        jMin = 0, jMax = 3;
    } else if(boxNum % 3 == 2) {
        jMin = 3, jMax = 6;
    } else if(boxNum % 3 == 0) {
        jMin = 6, jMax = 9;
    }

    for(int i = iMin; i < iMax; i++) {
        for(int j = jMin; j < jMax; j++) {
            output[i][j] = puzzle[i][j] + '0';
            box[k++] = puzzle[i][j];
        }
    }

    for(int i = 0; i < size; i++)
        for(int j = i + 1; j < size; j++) 
            if(box[i] == box[j]) 
                return 0;

    
    free(box);
    return 1;
}

int CheckPuzzle() {
    for(int i = 0; i < size; i++) 
        for(int j = 0; j < size; j++) 
            output[i][j] = puzzle[i][j] + '0';

    for(int i = 0; i < size; i++) 
        if(!(CheckRow(i) && CheckColumn(i) && CheckBox(i)))
            return 0;
    return 1;
}

int main(int argc, char *argv[]) {
    if(argc == 3 || argc < 2 || argc > 4) {
        fprintf(stderr, "To check if the entire puzzle is valid:\n");
        fprintf(stderr, "Usage: ./ValidSudoku <text file>\n");

        fprintf(stderr, "\nTo check if a specific row/column/box number is valid:\n");
        fprintf(stderr, "Usage: ./ValidSudoku <text file> <row/column/box> <number [1-9]>\n");

        exit(1);
    }

    if(!LoadFile(argv[1])) {
        fprintf(stderr, "Error loading file %s\n", argv[1]);
        exit(1);
    }

    PrintBoard(puzzle);
    printf("\n");
    int flag;

    if(argc == 2) {
        flag = CheckPuzzle();
        printf("Is a valid sudoku puzzle? %s\n", flag ? "true" : "false");
    } else if(argc == 4) {
        if(strcmp(argv[2], "row") == 0) {
            flag = CheckRow(atoi(argv[3]));
            printf("Is a valid row? %s\n", flag ? "true" : "false");
        } else if(strcmp(argv[2], "column") == 0) {
            flag = CheckColumn(atoi(argv[3]));
            printf("Is a valid column? %s\n", flag ? "true" : "false");
        } else if(strcmp(argv[2], "box") == 0) {
            flag = CheckBox(atoi(argv[3]));
            printf("Is a valid box? %s\n", flag ? "true" : "false");
        }
    }

    PrintOutput(output);
    return 0;
}