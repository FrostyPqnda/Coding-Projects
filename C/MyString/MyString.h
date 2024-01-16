#ifndef MYSTRING_H_INCLUDED
#define MYSTRING_H_INCLUDED

#define MAX 1000

typedef struct MyString {
    char* str;
} MyString;

// Initialize MyString value to src
MyString init(char* src);

// Append src to dest
MyString concat(MyString *dest, MyString src);

// Return a character at a given index of myStr
char charAt(MyString myStr, int idx);

// Compare two MyString object
int compare(MyString a, MyString b);

// Check if src starts with prefix
int startsWith(MyString src, MyString prefix);

// Check if src ends with suffix
int endsWith(MyString src, MyString suffix); 

// Check if the content of two MyString objects are equal
int equals(MyString myStr1, MyString myStr2);

// Search the first occurence of item in myStr
int index_char(MyString myStr, char item, int index);

// Search the first occurence of item in myStr
int index_MyString(MyString myStr, MyString item, int index);

// Search the last occurence of item in myStr
int last_index_char(MyString myStr, char item, int index);

// Search the last occurence of item in myStr
int last_index_MyString(MyString myStr, MyString item, int index);

// Return the length of myStr
int length(MyString myStr);

// Return the length of MyString array
int split_length(MyString myStr[MAX]);

// Replace every occurence of oldChar with newChar in myStr
MyString replace_char(MyString* myStr, char oldChar, char newChar);

// Replace every occurence of oldString with newString in myStr
MyString replace_MyString(MyString* myStr, MyString oldStr, MyString newStr);

// Split myStr into an array of MyString separated by the char delimiter
MyString* split_char(MyString myStr, char delim);

// Split myStr into an array of MyString separated by the MyString delimiter
MyString* split_MyString(MyString myStr, MyString delim);

// Extracts a substring from myString within range [start, end)
MyString substr(MyString myStr, int start, int end);

// Converts myStr to all lowercase
MyString lower(MyString* myStr);

// Converts myStr to all uppercase
MyString upper(MyString* myStr);

// Remove all leading and trailing whitespaces from myStr
MyString trim(MyString* myStr);

// Return myStr as char* object
char* toString(MyString myStr);

#endif