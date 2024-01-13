#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "Bank.h"

int numAccount = 0;
int min = 800001, max = 900000;

int findAccount(int accountID) {
    for(int i = 0; i < numAccount; i++)
        if(account[i].accountID == accountID)
            return i;
    return -1;
}

void addAccount(char* name, double balance) {
    srand(time(0));

    int userID;

    do {
        userID = (rand() % (max - min + 1)) + min;
    } while(findAccount(userID) != -1);

    strcpy(account[numAccount].name, name);
    account[numAccount].accountID = userID;
    account[numAccount].balance = balance;
    numAccount++;
}

void updateBalance(int accountID, double amount) {
    int idx = findAccount(accountID);
    if(idx < 0) {
        fprintf(stderr, "Account ID %d does not exist in the bank.\n", accountID);
        exit(-1);
    }

    float oldBalance = account[idx].balance;
    
    account[idx].balance += amount;
    if(account[idx].balance < 0) {
        account[idx].balance = oldBalance;
        fprintf(stderr, "Amount exceeds current balance.\n", accountID);
        exit(-1);
    }
}

void removeAccount(int accountID) {
    int idx = findAccount(accountID);
    if(idx < 0) {
        fprintf(stderr, "Account ID %d does not exist in the bank.\n", accountID);
        exit(-1);
    }

    int j = 0;
    for(int i = 0; i < numAccount; i++)
        if(i != idx)
            account[j++] = account[i];

    numAccount--;
}