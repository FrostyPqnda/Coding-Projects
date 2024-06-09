#include <stdlib.h>
#include <stdio.h>
#include "Stack.c"

/**
 * Compile: gcc Stacking.c -o <Output File>
 * Execute: ./<Output File>
 */
int main() {
    Stack* stack = NULL;
    push(&stack, 10);
    push(&stack, 20);
    printf("Top: %d\n", peek(stack));
}
