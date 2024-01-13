#include <errno.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/stat.h>
#include "Bank.h"

void printData(BankAccount user) {
    printf("%s %d %.2lf\n", user.name, user.accountID, user.balance);
}

void writeData(int fd, BankAccount user) {
    char s[MAX_LEN];
    sprintf(s, "%s %d %.2lf", user.name, user.accountID, user.balance);
    write(fd, s, strlen(s) + 1);
    write(fd, "\n", 1);
    return;
}

int loadData(int fd) {
    if(fd < 0) 
        return 0;

    char buf;
    int k = 0;
    while(read(fd, &buf, 1)) {
        account[numAccount].name[k++] = buf;
        if(buf == '\n') {
            numAccount++;
            k = 0;
        }
    }

    int i;
    char *search = " ", *token;
    for(i = 0; i < numAccount; i++) {
        token = strtok(account[i].name, search);

        token = strtok(NULL, search);
        account[i].accountID = atoi(token);

        token = strtok(NULL, search);
        account[i].balance = atof(token);
    }
    
    int userID = account[i].accountID + 1;
    return 1;
}

int main(int argc, char *argv[]) {
    if(argc <= 1) {
        fprintf(stderr, "./BankOperation <add/update/delete>\n");
        exit(1);
    }

    int fd;

    fd = open("bank.dat", O_RDONLY);
    int readFile = loadData(fd);
    fd = open("bank.dat", O_CREAT | O_WRONLY | O_TRUNC, 0600);

    if(strcmp(argv[1], "add") == 0) {
        if(argc != 4) {
            fprintf(stderr, "./BankOperation add <name> <deposit>\n");
            exit(1);
        }


        addAccount(argv[2], atof(argv[3]));
        for(int i = 0; i < numAccount; i++) {
            printData(account[i]);
            writeData(fd, account[i]);
        }
    } else if(strcmp(argv[1], "update") == 0) {    
        if(argc != 4) {
            fprintf(stderr, "./BankOperation update <account ID> <amount>\n");
            exit(1);
        }

        if(!readFile) {
            perror("");
            exit(1);
        }

        updateBalance(atoi(argv[2]), atof(argv[3]));  
        for(int i = 0; i < numAccount; i++) {
            printData(account[i]);
            writeData(fd, account[i]);
        }
    } else if(strcmp(argv[1], "delete") == 0) {
        if(argc != 3) {
            fprintf(stderr, "./BankOperation delete <account ID>\n");
            exit(1);
        }

        if(!readFile) {
            perror("");
            exit(1);
        }

        removeAccount(atoi(argv[2]));
        for(int i = 0; i < numAccount; i++) {
            printData(account[i]);
            writeData(fd, account[i]);
        }
    }

    close(fd);
}