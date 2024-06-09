#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include "MyString.c"

/**
 * Compile: gcc Main.c -o <Output File>
 * Execute: ./<Output File>
 */
int main() {
    MyString myStr = init("the quick brown fox jumps over the lazy dog");
    printf("%s\n", toString(myStr));

    MyString* split = split_char(myStr, ' ');
    int len = split_length(split);
    for(int i = 0; i < len; i++)
        printf("%s\n", split[i]);

    printf("%d\n", len);
}