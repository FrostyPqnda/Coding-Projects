#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include "MyString.h"

int main() {
    MyString myStr = init("the quick brown fox jumps over the lazy dog");
    printf("%s\n", toString(myStr));

    MyString* split = split_MyString(myStr, init("brown"));
    int len = split_length(split);
    for(int i = 0; i < len; i++)
        printf("%s,", split[i]);
}