from __future__ import annotations

class Node:
    # Constructor for Node
    def __init__(self, data = None):
        self.data = data
        self.next = None

class LinkedList:
    # Constructor for LinkedList
    def __init__(self, data: list = None):
        if data is None:
            self.head = None
        else:
            if not isinstance(data, list):
                raise TypeError('Must be of type <list>')
            
            self.head = Node(data[0]) if len(data) >= 1 else None
            for i in range(1, len(data)):
                self.insert(data[i])

    # Return the representation of the LinkedList object
    # as the memory address during runtime
    def __repr__(self) -> str:
        address = hex(id(self)).upper()
        rep = f'LinkedList<{address}>'
        return rep

    # Return the linked list as a string
    def __str__(self) -> str:
        curr = self.head
        data = ''

        while curr is not None:
            data += f'[{curr.data}] -> '
            curr = curr.next

        data = data[::-1].replace('->'[::-1], '', 1)[::-1].strip()
        return data

    # Insert node at the beginning of linked list
    def insertHead(self, data):
        node = Node(data)
        node.next = self.head
        self.head = node
        
    # Insert node at the end of linked list
    def insertTail(self, data):
        node = Node(data)
        node.next = None

        if self.head is None:
            self.head = node
            return
        
        temp = self.head
        while temp.next is not None:
            temp = temp.next
        temp.next = node

    def insert(self, data, index: int = None):
        # If position not speficied, insert node at the end of linked list
        if index is None:
            self.insertTail(data)
            return
        
        if not isinstance(index, int):
            raise TypeError('Must be of type <int>')
        
        # Invalid position
        if index < 0 or index >= self.size():
            raise IndexError('Index out of bound')
        
        # Position at the head of linked list, insert at front
        if index == 0:
            self.insertAtFront(data)
            return

        node = Node(data)
        ptr = self.head
        counter = 1

        while ptr is not None:
            if counter == index:
                node.next = ptr.next
                ptr.next = node
                break

            counter += 1
            ptr = ptr.next

    # Appends a linked list to the current linked list
    def append(self, link: LinkedList):
        if not isinstance(link, LinkedList):
            raise TypeError('Invalid type')

        add = link.head
        while add is not None:
            self.insert(add.data)
            add = add.next

    # Delete the head of the node and return the value at the deleted head
    def deleteHead(self):
        curr = self.head
        value = self.head.data

        if self.head is None:
            return
        
        curr = curr.next
        self.head = curr
        return value
    
    # Delete the tail of the node and return the value at the deleted tail
    def deleteTail(self):
        if self.head is None or self.head.next is None:
            return

        curr = self.head
        while curr.next.next is not None:
            curr = curr.next

        value = curr.next.data
        curr.next = None
        return value
    
    # Delete the node at the specified index and return the data inside the deleted node
    def delete(self, index: int = None):
        if self.head is None:
            return
        
        # If index not specified, remove last node
        if index is None:
            return self.deleteTail()
        
        # Invalid data type for index
        if not isinstance(index, int):
            raise TypeError('Must be of type <int>')
                
        if index < 0 or index >= self.size():
            raise IndexError('Index out of bound')

        # index at 0, delete the head node
        if index == 0:
            return self.deleteHead()    

        curr = self.head
        i = 0

        while i < index - 1 and curr is not None:
            i += 1
            curr = curr.next
        
        value = curr.next.data
        curr.next = curr.next.next
        return value
    
    # Remove a specified node from the linked list
    def remove(self, data):
        index = self.search(data)
        if index < 0:
            raise ValueError('Item not found!')
        
        self.delete(index)
        return
    
    # Remove all occurence of a node from the linked list
    def removeAll(self, data):
        """
        index = self.search(data)   
        if index < 0:
            raise ValueError('Item not found!')
        """
        for i in range(self.size() - 1, -1, -1):
            if self.get(i) == data:
                self.delete(i)

        return

    # Remove all nodes from linked list
    def clear(self):
        for i in range(self.size() - 1, -1, -1):
            self.delete(i)

    # Get the node at the specified index
    # If index is not specified, return as a list object
    def get(self, index: int = None):
        if index is None:
            stack = []
            curr = self.head
            while curr is not None:
                stack.append(curr.data)
                curr = curr.next
            return stack

        # If index is a non-integer value, then return it as an invalid data
        if not isinstance(index, int):
            raise TypeError('Must be of type <int>')
        
        # Index out of bounds
        if index < 0 or index >= self.size():
            raise IndexError('Index out of bound')
        
        value = None
        temp = self.head
        i = 0

        while temp is not None:
            if i == index:
                value = temp.data
                break
            i += 1
            temp = temp.next
          
        return value
    
    # Return the head of linked list
    def head(self):
        return self.get(0)
    
    # Return the tail of linked list
    def tail(self):
        return self.get(self.size() - 1)
        
    # Sets a specific node data to a new value
    def set(self, index: int, data):
        # If index is a non-integer value, then raise TypeError
        if not isinstance(index, int):
            raise TypeError('Must be of type <int>')
        
        if index < 0 or index >= self.size():
            raise IndexError('Index out of bound')
        
        temp = self.head
        i = 0

        while temp is not None:
            if i == index:
                temp.data = data
                break
            i += 1
            temp = temp.next
    
    # Search item in linked list
    def search(self, item, start: int = 0) -> int:
        # Search using binary search, best if sorted
        def binarySearch(item, left: int, right: int):
            mid = left + (right - left) // 2
            if left <= right:
                if self.get(mid) == item:
                    return mid
                elif self.get(mid) < item:
                    return binarySearch(item, mid + 1, right)
                else:
                    return binarySearch(item, left, mid - 1)
            else:
                return -1
        
        # Search using linear search
        def linearSearch(item, startIndex: int = 0):
            if startIndex < 0 or startIndex >= self.size():
                return -1

            for i in range(startIndex, self.size()):
                if self.get(i) == item:
                    return i
                
            return -1

        sorted = all(a <= b for a, b in zip(self.get(), self.get()[1:]))
        same = all(a == b for a, b in zip(self.get(), self.get()[1:]))
        # If linked list is already sorted, we want to use the most efficient search -> binary search
        # Otherwise, we will use linear search
        index = binarySearch(item, start, self.size() - 1) if sorted and not same else linearSearch(item, start)
        return index

    # Sort the array using Insertion Sort
    def sort(self, reversed = False):
        def sortedInsert(head: Node, node: Node):
            curr = None
            if head is None or head.data >= node.data:
                node.next = head
                head = node
            else:
                curr = head
                while curr.next is not None and curr.next.data < node.data:
                    curr = curr.next
                node.next = curr.next
                curr.next = node

            return head

        sorted = None
        curr = self.head
        while curr is not None:
            next = curr.next
            sorted = sortedInsert(sorted, curr)
            curr = next
        
        self.head = sorted
        if reversed:
            self.reverse()
    

    # Return size of linked list
    def size(self):
        temp = self.head
        size = 0
        while temp is not None:
            size += 1
            temp = temp.next
        return size

    # Reverse linked list
    def reverse(self):
        curr = self.head
        prev = None
        next = None

        while curr is not None:
            next = curr.next
            curr.next = prev
            prev = curr
            curr = next

        self.head = prev

    # Print linked list
    def print(self):
        temp = self.head
        if temp is None:
            print('Empty list')
            return
        
        while temp is not None:
            print(temp.data, end = ' ')
            temp = temp.next

        print()

if __name__ == '__main__':
    li = LinkedList()
    for i in range(10):
        li.insert(i + 1)
    print(li)
