#include <iostream>
using namespace std;

struct Node {
    int data;
    Node* next;

    Node(int data) {
        this->data = data;
        next = nullptr;
    }
};

struct LinkedList {
    Node* head;
    LinkedList() {head = nullptr;}

    void insert(int data) {
        Node* temp = new Node(data);
        temp->next = head;
        head = temp;
    }

    void print() {
        Node* temp = head;
        while(temp) {
            cout << temp->data << " ";
            temp = temp->next;
        }
    }

    void reverse() {
        Node* currNode = head;
        Node* prevNode = nullptr;
        Node* nextNode = nullptr;

        while(currNode != nullptr) {
            nextNode = currNode->next;
            currNode->next = prevNode;

            prevNode = currNode;
            currNode = nextNode;
        }

        head = prevNode;
        return;
    }
};

LinkedList li;

int main() {

    for(int i = 0; i < 10; i++) {
        li.insert((rand() % 100) + 1);
    }

    li.print();

    li.reverse();
    cout << endl;

    li.print();
}