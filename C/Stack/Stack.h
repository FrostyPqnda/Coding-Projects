#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#ifndef STACK_H_INCLUDED
#define STACK_H_INCLUDED

struct Stack {
    int top;
    unsigned capacity;
    int* array;
};

struct Stack* Stack();
int isEmpty(struct Stack *s);
int count(struct Stack *s);
void push(struct Stack *s, int value);
int pop(struct Stack *s);
int peek(struct Stack *s);
void expand(struct Stack *s);

#endif