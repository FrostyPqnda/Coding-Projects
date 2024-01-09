#include <stdlib.h>
#include <stdio.h>
#include "Stack.h"

int main() {
    Stack* stack = NULL;
    push(&stack, 10);
    push(&stack, 20);
    printf("Top: %d\n", peek(stack));
}
