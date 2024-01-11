#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#define MAXLEN 10000

char block[MAXLEN][MAXLEN];

int main(int argc, char* argv[]) {
    if(argc != 4) {
        fprintf(stderr, "gcc WordBlock.c -o WordBlock\n");
        fprintf(stderr, "./WordBlock <string> <row size> <column size>");
        exit(1);
    }

    char* str = argv[1];
    int row = atoi(argv[2]);
    int col = atoi(argv[3]);
    int index = 0;

    for(int i = 0; i < row; i++)
        for(int j = 0; j < col; j++) 
            block[i][j] = (index < strlen(str)) ? str[index++] : '-';

    for(int i = 0; i < row; i++) {
        for(int j = 0; j < col; j++) {
            printf("%c ", block[i][j]);
        }
        printf("\n");
    }
}
