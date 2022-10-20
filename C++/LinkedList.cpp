#include <iostream>
using namespace std;

struct Node {
    Node* next;
    int data;

    Node(int data) {
        this->data = data;
        next = nullptr;
    }
};

struct LinkedList {
    Node* head;
    LinkedList() {head = nullptr;}

    // Insert element into linked list
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

    int search(int elem) {
        Node* currNode = head;
        int count = 0;
        int idx = -1;

        while(currNode) {
            if(currNode->data == elem) {
                idx = count;
                break;
            } else {
                count++;
            }

            currNode = currNode->next;
        }
        
        return idx;
    }
};

LinkedList li;

int main() {

    for(int i = 0; i < 10; i++) {
        li.insert(i + 1);
    }

    li.print();
}