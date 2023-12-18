#include <stdlib.h>
#include <stdio.h>
#include "Stack.h"

int main() {
    struct Stack* stack = Stack();
    push(stack, 100);
    push(stack, 200);
    push(stack, 300);
    push(stack, 400);
    push(stack, 500);
    push(stack, 600);
    printf("Popped = %d\n", pop(stack));
}
