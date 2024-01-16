#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include "MyString.h"

#define MAX 1000

static void copystr(char* dest, char* src) {
    int i;
    for(i = 0; src[i] != '\0'; i++)
        dest[i] = src[i];
    dest[i] = '\0';
} 

MyString init(char* src) {
    MyString myStr;
    myStr.str = (char*)malloc(MAX * sizeof(char));
    copystr(myStr.str, src);
    return myStr;
}

MyString concat(MyString *dest, MyString src) {
    MyString str;
    str.str = (char*)malloc(MAX * sizeof(char));

    int k;
    for(k = 0; dest->str[k] != '\0'; k++) 
        str.str[k] = dest->str[k];


    for(int i = 0; src.str[i] != '\0'; i++)
        str.str[k++] = src.str[i];
        
    str.str[k] = '\0';
    *dest = init(str.str);    
    return *dest;
}

char charAt(MyString myStr, int idx) {
    if(idx < 0 || idx >= length(myStr)) {
        fprintf(stderr, "Index %d out of bound for string \'%s\'", idx, myStr.str);
        exit(-1);
    }

    return myStr.str[idx];
}

int compare(MyString a, MyString b) {
    int aLen = length(a), bLen = length(b);
    int min = (aLen <= bLen) ? aLen : bLen;

    for(int i = 0; i < min; i++)
        if(charAt(a, i) != charAt(b, i))
            return charAt(a, i) - charAt(b, i);

    return aLen - bLen;
}

int startsWith(MyString src, MyString prefix) {
    if(equals(src, prefix) || length(prefix) == 0)
        return 1;

    for(int i = 0; i <  length(prefix); i++) 
        if(charAt(src, i) != charAt(prefix, i))
            return 0;

    return 1;
}

int endsWith(MyString src, MyString suffix) {
    if(equals(src, suffix) || length(suffix) == 0)
        return 1;
    
}

int equals(MyString myStr1, MyString myStr2) {
    if(length(myStr1) != length(myStr2))
        return 0;

    for(int i = 0; myStr1.str[i] != '\0'; i++) 
        if(myStr1.str[i] != myStr2.str[i])
            return 0;

    return 1;
}

int index_char(MyString myStr, char item, int index) {
    if(index < 0 || index >= length(myStr)) {
        fprintf(stderr, "Index %d out of bound for string \'%s\'", index, myStr.str);
        exit(-1);
    }

    for(int i = index; myStr.str[i] != '\0'; i++) 
        if(myStr.str[i] == item)
            return i;
            
    return -1;
}

int index_MyString(MyString myStr, MyString item, int index) {
    if(index < 0 || index >= length(myStr)) {
        fprintf(stderr, "Index %d out of bound for string \'%s\'", index, myStr.str);
        exit(-1);
    }

    for(int i = index; i <= length(myStr) - length(item); i++) {
        int j = -1;
        if(charAt(myStr, i) == charAt(item, 0)) 
            for(j = 1; j < length(item); j++) 
                if(charAt(myStr, i + j) != charAt(item, j))
                    break;

        if(j == length(item))
            return i;
    }

    return -1;
}

int last_index_char(MyString myStr, char item, int index) {
    if(index < 0 || index >= length(myStr)) {
        fprintf(stderr, "Index %d out of bound for string \'%s\'", index, myStr.str);
        exit(-1);
    }

    for(int i = index; i >= 0; i--) 
        if(charAt(myStr, i) == item)
            return i;

    return -1;
}

int last_index_MyString(MyString myStr, MyString item, int index) {
    if(index < 0 || index >= length(myStr)) {
        fprintf(stderr, "Index %d out of bound for string \'%s\'", index, myStr.str);
        exit(-1);
    }

    for(int i = index; i >= 0; i--) {
        int n = i, j = -1;
        for(j = length(item) - 1; j >= 0; j--) {
            if(charAt(myStr, n) != charAt(item, j)) 
                break;
            n--;
            if(j == 0) 
                return n + 1;
        }
    }

    return -1;
}

int length(MyString myStr) {
    return strlen(myStr.str);
}

int split_length(MyString* myStr) {
    MyString* dup = myStr;
    int i = 0;

    while(dup && (dup + i++)->str);
    return i - 1;
}

MyString replace_char(MyString* myStr, char oldChar, char newChar) {
    for(int i = 0; i < length(*myStr); i++)
        if(charAt(*myStr, i) == oldChar)
            myStr->str[i] = newChar;

    return *myStr;
}

MyString replace_MyString(MyString* myStr, MyString oldStr, MyString newStr) {
    MyString clone = init(myStr->str);
    int index = index_MyString(*myStr, oldStr, 0);
    
    while(index != -1) {
        clone = substr(clone, 0, index);
        concat(&clone, newStr);
        int offset = index + length(oldStr);
        clone = concat(&clone, substr(*myStr, offset, length(*myStr)));
        index = index_MyString(clone, oldStr, 0);
    }

    return clone;
}

MyString* split_char(MyString myStr, char delim) {
    MyString* arr = (MyString*)malloc(MAX * sizeof(MyString));

    if(delim == 0) {
        for(int i = 0; i < length(myStr); i++)
            arr[i] = substr(myStr, i, i + 1);
        return arr;
    }

    int k = 0; 
    for(int i = 0; i < length(myStr); i++)
        if(myStr.str[i] == delim) 
            k++;
    
    int size = k + 1;
    int pos = 0, offset = 0, index = index_char(myStr, delim, 0);
    while(index != -1) {
        arr[pos++] = substr(myStr, offset, index);
        offset = index + 1;
        index = index_char(myStr, delim, offset);
    }
    arr[size - 1] = substr(myStr, offset, length(myStr));

    int stop = size - 1;
    while(equals(arr[stop], init("")))
        stop--;

    MyString* slice = (MyString*)malloc(MAX * sizeof(MyString));
    for(int i = 0; i <= stop; i++)
        slice[i] = init(arr[i].str);

    free(arr);
    return slice;
}

MyString* split_MyString(MyString myStr, MyString delim) {
    MyString* arr = (MyString*)malloc(MAX * sizeof(MyString));

    if(!length(delim)) {
        for(int i = 0; i < length(myStr); i++) 
            arr[i] = substr(myStr, i, i + 1);
        return arr;
    }

    int idx = index_MyString(myStr, delim, 0);
    while(idx != -1) 
        idx = index_MyString(myStr, delim, idx + 1);

    int pos = 0, offset = 0;
    idx = index_MyString(myStr, delim, 0);

    while(idx != -1) {
        arr[pos++] = substr(myStr, offset, idx);
        offset = idx + length(delim);
        idx = index_MyString(myStr, delim, idx + 1);
    }
    arr[pos] = substr(myStr, offset, length(myStr));


    return arr;
}

MyString substr(MyString myStr, int start, int end) {
    if(end < start || (start < 0 || start >= length(myStr)) || (end < 0 || end > length(myStr))) {
        fprintf(stderr, "Index out of bound: start %d, end %d, length %d", start, end, length(myStr));
        exit(-1);
    }

    int size = end - start;
    char* sub = (char*)malloc(size * sizeof(char));
    int k = 0;

    for(int i = start; i < end; i++)
        sub[k++] = myStr.str[i];

    sub[k] = '\0';
    return init(sub);
}

MyString lower(MyString* myStr) {
    for(int i = 0; i < length(*myStr); i++) 
        myStr->str[i] = tolower(myStr->str[i]);
    return *myStr;
}

MyString upper(MyString* myStr) {
    for(int i = 0; i < length(*myStr); i++) 
        myStr->str[i] = toupper(myStr->str[i]);
    return *myStr;
}

MyString trim(MyString* myStr) {
    int start = 0;
    while(charAt(*myStr, start++) == ' ');

    int end = length(*myStr) - 1;
    while(charAt(*myStr, start--) == ' ');

    *myStr = substr(*myStr, start, end + 1);
    return *myStr;
}

char* toString(MyString myStr) {
    return myStr.str;
}