#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#ifndef BANK_H_INCLUDED
#define BANK_H_INCLUDED 

#define MAX_ACCOUNT 10000
#define MAX_LEN 100

typedef struct BankAccount {
    char name[MAX_LEN];
    int accountID;
    double balance;
} BankAccount;

BankAccount account[MAX_ACCOUNT];
extern int numAccount;

int findAccount(int accountID);
void addAccount(char* name, double balance);
void updateBalance(int accountID, double amount);
void removeAccount(int accountID);

#endif