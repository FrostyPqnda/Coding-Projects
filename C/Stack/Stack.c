#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "Stack.h"

#define DEFAULT_SIZE 100

struct Stack* Stack() {
    struct Stack* stack = (struct Stack*)malloc(sizeof(struct Stack));
    stack->top = -1;
    stack->array = (int*)malloc(DEFAULT_SIZE * sizeof(int));
    return stack;
}

int isEmpty(struct Stack *s) {
    return s->top == -1;
}

int count(struct Stack *s) {
    return s->top + 1;
}

void push(struct Stack *s, int value) {
    expand(s);
    s->array[++s->top] = value;
}

int pop(struct Stack *s) {
    if(isEmpty(s)) {
        fprintf(stderr, "Empty stack\n");
        exit(1);
    }

    return s->array[s->top--];
}

int peek(struct Stack *s) {
    if(isEmpty(s)) {
        fprintf(stderr, "Empty stack\n");
        exit(1);
    }
    
    return s->array[s->top];
}

void expand(struct Stack *s) {
    int currSize = count(s);
    if(currSize < sizeof(s->array) / sizeof(s->array[0]))
        return;
     
    s->array = (int *)realloc(s->array, sizeof(int) * currSize);
}