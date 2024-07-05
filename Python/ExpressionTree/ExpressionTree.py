from __future__ import annotations
import re

class ExpressionTree:
    """
    A class used to represent an Expression Tree

    Attributes:
    expr : str | list[str]
        An arithmetic expression

    Methods:
    evaluate()
        Evaluautes the expression tree

    DFS(traversal='inorder')
        Get a list of symbols using depth-first search

    BFS()
        Get a list of symbols at every level using
        breadth-first search

    height()
        Get the height of the expression tree from the 
        root to the leaf

    Known Errors:
    1. Command-Line argument does not work if the user
    decides to put in parantheses in the expression.
    The only work-around would be to use [] in
    place of ().
    """
    class __Symbol:
        """
        A class used to represent the symbol (operand / operator) of an
        Expression Tree

        Attributes:
        symbol : str
            A string representing an operand / operator of a node in
            the expression tree
        left : ExpressionTree.__Symbol
            The left child of the node (default is None)
        right : ExpressionTree.__Symbol
            The right child of the node (default is None) 

        Methods:
        __str__(self):
            Return the symbol (operand / operator) of the node
        """
        def __init__(self, symbol: str, left: ExpressionTree.__Symbol = None, right: ExpressionTree.__Symbol = None):
            self.symbol: str = symbol
            self.left: ExpressionTree.__Symbol = left
            self.right: ExpressionTree.__Symbol = right

        def __str__(self):
            return self.symbol
    
    def __init__(self, expr: str | list[str]) -> ExpressionTree:
        if not expr:
            raise ExpressionTreeError('The expression cannot be null!')
        
        if not isinstance(expr, str | list) or not all(isinstance(item, str) for item in expr): 
            raise ExpressionTreeError('The expression could not parsed!')

        self.__root: ExpressionTree.__Symbol = self.__buildTree(expr)
        self.__solvable: bool = not any(re.search('[a-zA-Z]+', s) for s in expr)
        

    def __str__(self) -> str:
        """
        Return the arithmetic expression of the tree
        """
        return self.__expr
    
    def __repr__(self) -> str:
        """
        Return the object representation of the Expression
        Tree class.
        """
        return f'ExpressionTree(\'{self.__expr}\')'
    
    def __eq__(self, other: ExpressionTree) -> bool:
        """
        Check if two trees are equal

        Parameters:
        other : ExpressionTree
            The tree to be compared to

        Return
        True if the two trees are equal
        """
        if not isinstance(other, ExpressionTree):
            return False
        
        def sameTree(rootA: ExpressionTree.__Symbol, rootB: ExpressionTree.__Symbol):
            """
            Check if every node in rootA and rootB are
            equal.

            Paramaters:
            rootA | ExpressionTree.__Symbol
                The root of ExpressionTree A
            rootB | ExpressionTree.__Symbol
                The root of ExpressionTree B

            Return
            True if every node in rootA and rootB are equal
            """
            if not (rootA or rootB):
                return True
            if not (rootA and rootB):
                return False
            if rootA.symbol != rootB.symbol:
                return False
            
            leftTree: bool = sameTree(rootA.left, rootB.left)
            rightTree: bool = sameTree(rootA.right, rootB.right)
            return leftTree and rightTree
        
        return sameTree(self.__root, other.__root)

    
    def __buildTree(self, expr: str | list[str]) -> ExpressionTree.__Symbol:
        """
        Construct the expression tree

        Parameters:
        expr : str | list[str]
            An arithmetic (infix) expression

        Return:
        The root of the expression tree
        """
        def isOperator(op: str) -> bool:
            """
            Check if op is a valid operator

            Parameters:
            op : str
                The string to be tested

            Return:
            True if op is a valid operator
            """
            return op in ['+', '-', '*', '/', '^']
        
        def precedence(op: str) -> int:
            """
            Get the precedence of the operator

            Parameters:
            op : str
                An arithmetic operator

            Return:
            The precedence of the operator
            """
            match op:
                case '^':
                    return 3
                case '*' | '/':
                    return 2
                case '+' | '-':
                    return 1
                case default:
                    return -1
                
        def buildNode(node2: ExpressionTree.__Symbol, node1: ExpressionTree.__Symbol, symbol: str) -> ExpressionTree.__Symbol:
            """
            Construct a new node for the expression tree

            Parameters:
            node2 : ExpressionTree.__Symbol
                The right child of the node
            node1 : ExpressionTree.__Symbol
                The left child of the node
            symbol : str
                The operand / operator of the node

            Return:
            The new node for the expression tree
            """
            return self.__Symbol(symbol, node1, node2)
       
        def tokenize(expr: str | list[str]) -> list[str]:
            """
            Tokenize the arithmetic expression

            Parameters:
            expr : str | list[str]
                The arithmetic expression to be tokenized

            Return:
            A list of tokens parsed from the expression
            """
            expr: str = ''.join(expr) \
            .replace('[', '(') \
            .replace(']', ')') \
            .replace('+-', '-') \
            .replace('-+', '-') \
            .replace('--', '+') \
            .replace('**', '^') \
            .replace('//', '/') \
            .replace(')(', ")*(") \
            .upper()

            pattern = r'^[+-]?([A-Z]+|([0-9]+([.][0-9]+)?|[.][0-9]+))$'

            numOperators: int = 0
            numOperands: int = 0
            numOpenParen: int = 0
            numClosedParen: int = 0

            tokens = []
            i = 0
            while i < len(expr):
                curr = expr[i]
                if isOperator(curr):
                    numOperators += 1
                    tokens.append(curr)
                elif curr == '(':
                    numOpenParen += 1
                    if tokens and re.search(pattern, tokens[-1]):
                        numOperators += 1
                        tokens.append('*')
                    tokens.append(curr)
                elif curr == ')':
                    numClosedParen += 1
                    tokens.append(curr)
                else:
                    numOperands += 1
                    # Extract the values
                    while i + 1 < len(expr) and not isOperator(expr[i + 1]) and expr[i + 1] != '(' and expr[i + 1] != ')':
                        curr += expr[i + 1]
                        i += 1

                    curr = curr.strip()
                    if not re.match(pattern, curr):
                        raise ExpressionTreeError('Failed to parse operand!')
                    
                    # First item in token is a '-'
                    if len(tokens) == 1 and tokens[-1] == '-':
                        numOperators -= 1
                        tokens[-1] = f'-{curr}'
                    # The token before the last token is either an operator or '('
                    elif len(tokens) >= 2 and (isOperator(tokens[-2]) or tokens[-2] == '(') and tokens[-1] == '-':
                        numOperators -= 1
                        tokens[-1] = f'-{curr}'
                    else:
                        tokens.append(curr)
                i += 1

            if numOperators == 0:
                raise ExpressionTreeError('No arithmetic operators detected')

            if numOperators >= numOperands:
                raise ExpressionTreeError('Operator count >= Operand count')
            
            if numOpenParen != numClosedParen:
                raise ExpressionTreeError('Unbalanced expression!')

            self.__expr = ' '.join(tokens).strip()
            return tokens
           
        tokens: list[str] = tokenize(expr)
        print(tokens)
        operands: list[ExpressionTree.__Symbol] = []
        operators: list[str] = []

        for token in tokens:
            if isOperator(token):
                while operators and precedence(token) <= precedence(operators[-1]):
                    operands.append(buildNode(operands.pop(), operands.pop(), operators.pop()))
                operators.append(token)
            elif token == '(':
                operators.append(token)
            elif token == ')':
                while operators and operators[-1] != '(':
                    operands.append(buildNode(operands.pop(), operands.pop(), operators.pop()))
                operators.pop()
            else:
                operands.append(self.__Symbol(token))

        while operators:
            operands.append(buildNode(operands.pop(), operands.pop(), operators.pop()))

        return operands.pop()

    def evaluate(self) -> float | int | None:
        """
        Performs arithmetic evaluation on the expression tree

        Return:
        An evaluation of the expression tree
        """
        if not self.__solvable:
            return None
        
        def eval(root: ExpressionTree.__Symbol) -> float | int:
            """
            Performs an arithmetic evaluation starting at 
            the root of the tree

            Parameters:
            root : ExpressionTree.__Symbol
                The root of the tree

            Return:
            The arithmetic evaluation of the tree
            """
            if root.left == root.right == None:
                return float(root.symbol)
            
            left: float | int = eval(root.left)
            right: float | int = eval(root.right)
            value: float | int = -1

            match root.symbol:
                case '^':
                    value = left ** right
                    return int(value) if value.is_integer() else value
                case '*':
                    value = left * right
                    return int(value) if value.is_integer() else value
                case '/':
                    if right == 0:
                        raise ZeroDivisionError()
                    value = left / right
                    return int(value) if value.is_integer() else value
                case '+':
                    value = left + right
                    return int(value) if value.is_integer() else value
                case '-':
                    value = left - right
                    return int(value) if value.is_integer() else value
        
        return eval(self.__root)

    def DFS(self, traversal: str = 'inorder') -> list[str]:
        """
        Retrieves a list of symbols after performing
        Depth-First Search traversal on the expression
        tree.

        Parameters:
        traversal : str
            The DFS traversal type (default is 'inorder')

        Raises:
        TypeError
            If traversal is not of type <str>
        
        KeyError
            If traversal is not a valid traversal type

        Return:
        A list of symbols after performing depth-first search
        on the expression tree.
        """
        if not isinstance(traversal, str):
            raise TypeError('<traversal> must be of type <str>')
        
        def preorder(root: ExpressionTree.__Symbol, symbols: list[str] = []) -> list[str]:
            """
            Get a list of symbols after performing preorder traversal on
            the tree

            Parameters:
            root : ExpressionTree.__Symbol
                The root of the tree
            symbols : list[str]
                A list of symbols (default is [])
            
            Return:
            A list of symbols
            """
            if not root:
                return []
            
            symbols.append(root.symbol)     
            preorder(root.left)
            preorder(root.right)
            return symbols

        def postorder(root: ExpressionTree.__Symbol, symbols: list[str] = []) -> list[str]:
            """
            Get a list of symbols after performing postorder traversal on
            the tree

            Parameters:
            root : ExpressionTree.__Symbol
                The root of the tree
            symbols : list[str]
                A list of symbols (default is [])
            
            Return:
            A list of symbols
            """
            if not root:
                return []
            
            postorder(root.left)
            postorder(root.right)
            symbols.append(root.symbol)     
            return symbols
        
        def inorder(root: ExpressionTree.__Symbol, symbols: list[str] = []) -> list[str]:
            """
            Get a list of symbols after performing inorder traversal on
            the tree

            Parameters:
            root : ExpressionTree.__Symbol
                The root of the tree
            symbols : list[str]
                A list of symbols (default is [])
            
            Return:
            A list of symbols
            """
            if not root:
                return []
            
            inorder(root.left)
            symbols.append(root.symbol)        
            inorder(root.right)
            return symbols
        
        traversals = {
            'preorder': preorder,
            'postorder': postorder,
            'inorder': inorder
        }

        if traversal.lower() not in traversals:
            raise KeyError(f'Key {traversal} is not a valid traversal type')

        return traversals[traversal](self.__root)
    
    
    def BFS(self) -> list[list[str]]:
        """
        Retrieves a list of symbols after performing 
        Breadth-First Search (Level-Order) traversal on
        the expression tree.

        Return:
        A list of symbols at each level of the expression tree
        from left to right
        """ 
        def levelOrder(root: ExpressionTree.__Symbol) -> list[list[str]]:
            """
            Helper function to retrieve a list of symbols after performing 
            Breadth-First Search (Level-Order) traversal on
            the expression tree.

            Parameters:
            root : ExpressionTree.__Symbol
                The root of the expression tree

            Return:
            A list of symbols at each level of the expression tree
            from left to right
            """
            levels: list[list[str]] = []
            queue: list[ExpressionTree.__Symbol] = [root]

            while queue:
                numChild = len(queue)
                children: list[str] = []

                for _ in range(numChild):
                    child = queue.pop(0)
                    if child:
                        children.append(child.symbol)
                        queue.append(child.left)
                        queue.append(child.right)

                if children:
                    levels.append(children)

            return levels

        return levelOrder(self.__root)
    
    def height(self) -> int:
        """
        Get the height of the expression tree

        Return:
        The height of the expression tree
        """
        def treeHeight(root: ExpressionTree.__Symbol) -> int:
            """
            Get the height of a specified root in the 
            expression tree

            Parameters:
            root : ExpressionTree.__Symbol
                The root of the expression tree

            Return:
            The height of a specified root in the
            expression tree
            """
            if not root:
                return -1
            
            leftHeight: int = treeHeight(root.left)
            rightHeight: int = treeHeight(root.right)
            return 1 + max(leftHeight, rightHeight)
        
        return treeHeight(self.__root)
    
    def print(self) -> None:
        """
        Print the tree representation of the expression tree
        """
        if not self.__root:
            return
        
        def depth(root: ExpressionTree.__Symbol) -> int:
            """
            Return the depth of current root in the tree.

            Parameters:
            root : ExpressionTree.__Symbol
                The root of the tree

            Return:
            The depth of the tree
            """
            if not root:
                return 0
            leftDepth = depth(root.left)
            rightDepth = depth(root.right)
            return 1 + max(leftDepth, rightDepth)
        
        def getColumn(h: int) -> int:
            """
            Get the current column of h

            Parameters:
            h : int
                Specified height value

            Return:
            The specified column of h
            """
            if h == 1:
                return 1
            return (2 * getColumn(h - 1)) + 1
        
        def buildTreeStructure(root: ExpressionTree.__Symbol, col: int, row: int, height: int) -> None:
            """
            Build the expression tree as a tree structure using preorder depth-first
            search.

            Parameters:
            root : ExpressionTree.__Symbol
                The root of the tree
            col : int
                The column value
            row : int
                The row value
            height:
                The height value
            """
            if not root:
                return
            T[row][col] = root.symbol
            buildTreeStructure(
                root.left, col - pow(2, height - 2), row + 1, height - 1
            )
            buildTreeStructure(
                root.right, col + pow(2, height - 2), row + 1, height - 1
            )

        h = depth(self.__root)
        c = getColumn(h)
        T = [[None for _ in range(c)] for _ in range(h)]
        buildTreeStructure(self.__root, c // 2, 0, h)
        for i in range(h):
            for j in range(c):
                if T[i][j]:
                    print(f'{T[i][j]} ', end = ' ')
                else:
                    print('  ', end = ' ')
            print()


class ExpressionTreeError(Exception):
    """
    Class used to represent any errors that are detected
    due to faulty logic with the Expression Tree class.
    """
    pass
