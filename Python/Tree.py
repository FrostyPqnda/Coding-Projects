from __future__ import annotations

"""
Implementation of the self-balancing
AVL Tree
"""
class Tree:
    class _TreeNode:
        def __init__(self, data):            
            self.data = data
            self.height = 0
            self.left = None
            self.right = None

    def __init__(self):
        self.root = None

    # Insert item into the tree
    def insert(self, item):        
        # Rotate tree left
        def rotateLeft(k2):
            k1 = k2.right # Copy of k2's right subtree
            k3 = k1.left # Copy of k2.right's left subtree

            k2.right = k3 # Set k2.right to k2.right.left
            k1.left = k2 # Set k2.right.left to k2

            k2.height = max(self._getHeight(k2.left), self._getHeight(k2.right)) + 1
            k1.height = max(self._getHeight(k1.left), self._getHeight(k1.right)) + 1

            return k1
        
        # Rotate tree right
        def rotateRight(k2):
            k1 = k2.left # Copy of k2's left subtree
            k3 = k1.right # Copy of k2.left's right subtree
            
            k2.left = k3 # k2.left = k2.left.right
            k1.right = k2 # k2.left.right = k2

            k2.height = max(self._getHeight(k2.left), self._getHeight(k2.right)) + 1
            k1.height = max(self._getHeight(k1.left), self._getHeight(k1.right)) + 1

            return k1
        
        # Rotate tree left then right
        def rotateLeftRight(k3):
            k3.left = rotateLeft(k3.left)
            return rotateRight(k3)
        
        # Rotate tree right then left
        def rotateRightLeft(k3):
            k3.right = rotateRight(k3.right)
            return rotateLeft(k3)
        
        # Balance the tree
        def balance(root):
            if not root: return root

            leftMost = self._getHeight(root.left) - self._getHeight(root.right)
            rightMost = self._getHeight(root.right) - self._getHeight(root.left)
            
            if leftMost > 1:
                left_LeftHeight = self._getHeight(root.left.left)
                left_RightHeight = self._getHeight(root.left.right)
                if left_LeftHeight >= left_RightHeight:
                    root = rotateRight(root)
                else:
                    root = rotateLeftRight(root)
            else:
                if rightMost > 1:
                    right_RightHeight = self._getHeight(root.right.right)
                    right_LeftHeight = self._getHeight(root.right.left)
                    if right_RightHeight >= right_LeftHeight:
                        root = rotateLeft(root)
                    else:
                        root = rotateRightLeft(root)

            root.height = 1 + max(self._getHeight(root.left), self._getHeight(root.right))
            return root
        
        # Insert item at a specific root
        def insertAtRoot(root, item: int):
            if not root: return self._TreeNode(item)
            
            if item < root.data: 
                root.left = insertAtRoot(root.left, item)
            elif item > root.data:
                root.right = insertAtRoot(root.right, item)

            return balance(root)

        self.root = insertAtRoot(self.root, item)

    # Delete the specified node from the tree
    def delete(self, item) -> None:
        def minValue(root):
            minv = root.data
            while root.left:
                minv = root.left.data
                root = root.left
            return minv
        
        def deleteNode(root, key):
            if not root: return root

            if key < root.data: 
                root.left = deleteNode(root.left, key)
            elif key > root.data:
                root.right = deleteNode(root.right, key)
            else:
                if not root.left: return root.right
                elif not root.right: return root.left

                root.data = minValue(root.right)
                root.right = deleteNode(root.right, root.data)

            return root
        
        self.root = deleteNode(self.root, item)
    
    # Invert the tree
    # Swap each left and right subtrees
    def invert(self):
        def mirror(root):
            if not root: return

            root.left, root.right = root.right, root.left
            
            mirror(root.left)
            mirror(root.right)
        
        mirror(self.root)

    # Return all possibles paths in the tree
    def allPaths(self) -> list[list[_TreeNode]]:
        def findPaths(root, path = [], pathLen = 0, paths = []):
            if not root: return

            if len(path) > pathLen:
                path[pathLen] = root.data
            else:
                path.append(root.data)

            pathLen += 1
            
            if not root.left and not root.right:
                paths.append(path[:])

            findPaths(root.left, path, pathLen, paths)
            findPaths(root.right, path, pathLen, paths)

            return paths

        return findPaths(self.root, [], 0, [])

    # Check if an item exists in a tree using BFS
    def find(self, item: int) -> bool:
        return item in self.BFS()

    # Delete the tree
    def clear(self) -> None:
        self.root = None

    # Get the height of the tree
    def height(self) -> int:
        return self._getHeight(self.root)

    # Helper method to get height of a tree
    def _getHeight(self, root) -> int:
        return 1 + max(self._getHeight(root.left), self._getHeight(root.right)) if root else 0
    
    # Check if the tree is balanced
    # A tree is balanced if the height of every left subtree differ
    # from the height of every right subtree by 1
    def isBalanced(self):
        def balanced(root):
            if not root: return True

            lh = self._getHeight(root.left)
            rh = self._getHeight(root.right)

            return abs(lh - rh) <= 1 and balanced(root.left) and balanced(root.right)
        
        return balanced(self.root)
    
    # Print the Tree as a tree structure
    def print(self):
        def printTree(T, root, col, row, height):
            if not root: return
            T[row][col] = root.data
            printTree(T, root.left, col - pow(2, height - 2), row + 1, height - 1)
            printTree(T, root.right, col + pow(2, height - 2), row + 1, height - 1)

        def getcol(h):
            if h == 1: return 1
            return (2 * getcol(h - 1)) + 1
        
        if not self.root: return
        
        height = self.height()
        col = getcol(height)
        M = [[0 for _ in range(col)] for _ in range(height)]
        printTree(M, self.root, col // 2, 0, height)

        for i in M:
            for j in i:
                print(' ' if j == 0 else j, end = ' ')
            print('')

    # Return the tree after performing Breadth-First Search
    def BFS(self) -> list[_TreeNode]:
        if not self.root: return []
        
        queue, bfs = [], []
        queue.append(self.root)

        while len(queue) > 0:
            bfs.append(queue[0].data)
            node = queue.pop(0)

            if node.left:
                queue.append(node.left)
            
            if node.right:
                queue.append(node.right)

        return bfs

    # Return the tree after performing Depth-First Search
    # Default traversal is Inorder Traversal
    def DFS(self, type = 'inorder') -> list[_TreeNode]:
        def preorder(root, nodes = []):
            if not root: return []
            nodes.append(root.data)
            preorder(root.left)
            preorder(root.right)
            return nodes
        
        def postorder(root, nodes = []):
            if not root: return []
            postorder(root.left)
            postorder(root.right)
            nodes.append(root.data)
            return nodes
        
        def inorder(root, nodes = []):
            if not root: return []
            inorder(root.left)
            nodes.append(root.data)
            inorder(root.right)
            return nodes
        
        traversals = {
            'preorder': preorder,
            'postorder': postorder,
            'inorder': inorder
        }

        if type not in traversals:
            raise KeyError(f'Key {type} is not a valid traversal type')
        
        return traversals[type](self.root)

    def __add__(self, other: Tree) -> Tree:
        if not isinstance(other, Tree):
            raise TypeError(f'{other} is not of type <Tree>')
        
        def merge(root):
            if not root: return 

            self.insert(root.data)

            merge(root.left)
            merge(root.right)

        merge(other.root)
        return self

if __name__ == '__main__':
    tree = Tree()
    nums = [4,2,7,1,3,6,9]
    for num in nums:
        tree.insert(num)
    tree.print()