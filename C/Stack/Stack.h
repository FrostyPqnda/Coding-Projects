#include <stdio.h>
#include <stdlib.h>

#ifndef STACK_H_INCLUDED
#define STACK_H_INCLUDED

typedef struct Stack {
    int data;
    struct Stack* next;
} Stack;

int isEmpty(Stack* head);
void push(Stack** head, int data);
int pop(Stack** head);
int peek(Stack* head);
int size(Stack* head);

#endif