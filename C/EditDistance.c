#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MIN(a, b) (a < b) ? a : b

// Calculate the minimum number of distance to convert
// String a to String b using the following operations:
// Insert
// Delete
// Replace
int editDistance(char* a, char* b) {
    int m = strlen(a), n = strlen(b);

    int** edit = (int**)malloc((m + 1) * sizeof(int*));
    for(int i = 0; i <= m; i++)
        edit[i] = (int*)malloc((n + 1) * sizeof(int));

    for(int i = 0; i <= m; i++) {
        for(int j = 0; j <= n; j++) {
            edit[i][j] = 0;
        }
    }

    for(int j = 1; j <= n; j++)
        edit[0][j] = j;

    for(int i = 1; i <= m; i++) {
        edit[i][0] = i;
        for(int j = 1; j <= n; j++) {
            int ins = edit[i][j - 1] + 1;
            int del = edit[i - 1][j] + 1;
            int rep = (a[i - 1] == b[j - 1]) ? edit[i - 1][j - 1] :  edit[i - 1][j - 1] + 1;

            int pMin = MIN(ins, del), qMin = MIN(del, rep);
            edit[i][j] = MIN(pMin, qMin);
        }
    }

    int minEdit = edit[m][n];

    // Free the dynamic 2D array
    for(int i = 0; i <= m; i++)
        free(edit[i]);
    free(edit);

    return minEdit;
}

int main(int argc, char *argv[]) {
    if(argc != 3) {
        fprintf(stderr, "gcc EditDistance.c -o EditDistance\n");
        fprintf(stderr, "./EditDistance <String A> <String B>\n");
        exit(1);
    }

    char* aStr = argv[1];
    char* bStr = argv[2];
    int minEdit = editDistance(aStr, bStr);

    printf("The minimum no. of operations to convert \"%s\" to \"%s\" is %d\n", aStr, bStr, minEdit);
    return 0;
}