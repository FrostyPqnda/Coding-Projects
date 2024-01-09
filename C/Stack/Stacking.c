#include <stdlib.h>
#include <stdio.h>
#include "Stack.h"

int main() {
    Stack* stack = NULL;
    push(&stack, 10);
    push(&stack, 20);
    push(&stack, 30);
    printf("Popped: %d\n", pop(&stack));
}
