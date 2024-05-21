"""
An implementation of the Stack class that retrieves the minimum value in 
the stack and uses the linked list implementation.
"""

class MinStack:
    # Store the item of MinStack as a Node object
    class Node:
        def __init__(self, minValue: int, data: int):
            self.data = data
            self.minValue = minValue
            self.next = None
    
    # Initialize MinStack as a linked list
    def __init__(self):
        self.head = None
    
    # Push an item onto the top of the stack
    def push(self, data: int):
        if not type(data) is int:
            raise TypeError(f'{data} is not of type <int>')
        
        head = self.head
        node = self.Node(data, data) if not head else self.Node(min(head.minValue, data), data)
        node.next = self.head
        self.head = node

    # Remove the topmost item from the stack
    def pop(self) -> int:
        if self.isEmpty():
            raise ValueError('Stack is empty')
        
        popped = self.head.data
        self.head = self.head.next
        return popped
    
    # Get the topmost item in the stack
    def peek(self) -> int:
        if self.isEmpty():
            raise ValueError('Stack is empty')
        
        return self.head.data
    
    # Get the minimum value of the stack
    def getMin(self) -> int:
        return self.head.minValue

    # Check if the stack is empty
    def isEmpty(self):
        return not self.head
    
    # Get the size of the stack
    def size(self):
        curr = self.head
        size = 0
        
        while curr:
            size += 1
            curr = curr.next

        return size
    
if __name__ == '__main__':
    stack = MinStack()
    
    stack.push(-2)
    stack.push(0)
    stack.push(-3)

    print(f'Min = {stack.getMin()}')
    stack.pop()
    print(f'Top = {stack.peek()}')
    print(f'Min = {stack.getMin()}')
    

    

    
        
            
        
