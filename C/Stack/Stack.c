#include <stdio.h>
#include <stdlib.h>
#include "Stack.h"

Stack* newNode(int data) {
    Stack* stackNode = (Stack*)malloc(sizeof(Stack));
    stackNode->data = data;
    stackNode->next = NULL;
    return stackNode;
}

int isEmpty(Stack* head) {
    return !head;
}

void push(Stack** head, int data) {
    Stack* stackNode = newNode(data);
    stackNode->next = *head;
    *head = stackNode;
}

int pop(Stack** head) {
    if(isEmpty(*head)) {
        fprintf(stderr, "Empty stack\n");
        exit(1);
    }

    Stack* tmp = *head;
    *head = (*head)->next;
    int popped = tmp->data;
    free(tmp);
    return popped;
}

int peek(Stack* head) {
    if(isEmpty(head)) {
        fprintf(stderr, "Empty stack\n");
        exit(1);
    }
    
    return head->data;
}

int count(Stack* head) {
    if(isEmpty(head))
        return 0;

    Stack* tmp = head;
    int numItems = 0;
    while(tmp) {
        numItems++;
        tmp = tmp->next;
    }
    return numItems;
}