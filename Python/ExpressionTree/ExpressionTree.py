from __future__ import annotations
import re

class ExpressionTree:
    """
    A class used to represent an Expression Tree

    Attributes:
    expr : str | list[str]
        A formatted string that represents an arithmetic expression

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
    This is because the shell / terminal has higher
    priority than the compiler.

    To-dos:
    1. Finish the print() method
    """
    class _Symbol:
        """
        A class used to represent the symbol (operand / operator) of an
        Expression Tree

        Attributes:
        symbol : str
            A string representing an operand / operator of a node in
            the expression tree
        left : ExpressionTree._Symbol
            The left child of the node (default is None)
        right : ExpressionTree._Symbol
            The right child of the node (default is None) 

        Methods:
        __str__(self):
            Return the symbol (operand / operator) of the node
        """
        def __init__(self, symbol: str, left: ExpressionTree._Symbol = None, right: ExpressionTree._Symbol = None):
            self.symbol: str = symbol
            self.left: ExpressionTree._Symbol = left
            self.right: ExpressionTree._Symbol = right

        def __str__(self):
            return self.symbol
    
    def __init__(self, expr: str | list[str]) -> ExpressionTree:
        if not expr:
            raise ValueError('<expr> cannot be null!')

        self.root: ExpressionTree._Symbol = self.__buildTree(expr)
        self.solvable: bool = not any(re.search('[a-zA-Z]', s) for s in expr)
    
    def __buildTree(self, expr: str | list[str]) -> ExpressionTree._Symbol:
        """
        Construct the expression tree

        Parameters:
        expr : str | list[str]
            An arithmetic (infix) expression

        Return:
        ExpressionTree._Symbol
            The root of the expression tree
        """
        def isOperator(op: str) -> bool:
            """
            Check if op is a valid operator

            Parameters:
            op : str
                The string to be tested

            Return:
            bool
                True if op is a valid operator
            """
            return op in ['+', '-', '*', '/', '^']
        
        def precedence(op: str) -> int:
            """
            Get the precedence of the operator

            Parameters:
            op : str
                The string to set the precedence

            Return:
            int
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
                
        def buildNode(node2: ExpressionTree._Symbol, node1: ExpressionTree._Symbol, symbol: str) -> ExpressionTree._Symbol:
            """
            Construct a new node for the expression tree

            Parameters:
            node2 : ExpressionTree._Symbol
                The right child of the node
            node1 : ExpressionTree._Symbol
                The left child of the node
            symbol : str
                The operand / operator of the node

            Return:
            ExpressionTree._Symbol
                The new node for the expression tree
            """
            return self._Symbol(symbol, node1, node2)
       
        def tokenize(expr: str | list[str]) -> list[str]:
            """
            Tokenize the arithmetic expression

            Parameters:
            expr : str | list[str]
                The arithmetic expression to be tokenized

            Return:
            list[str]
                A list of tokens parsed from the expression
            """
            expr = ''.join(expr) \
            .replace('(-', '(0-') \
            .replace('+-', '-') \
            .replace('-+', '-') \
            .replace('--', '+') \
            .replace('**', '^') \
            .replace('//', '/') \
            .replace(')(', ")*(")

            if expr.startswith('-'):
                expr = '0' + expr

            formatted = ''
            i = 0  

            while i < len(expr):
                c = expr[i]
                if isOperator(c):
                    formatted += f' {c} '
                    if i + 1 < len(expr) and expr[i + 1] == '-':
                        formatted += f'{expr[i + 1]}'
                        i += 1
                elif c == '(' or c == ')':
                    formatted += f' {c} '
                else:
                    formatted += f'{c}'
                
                i += 1
            
            formatted = re.sub(r'\s+', ' ', formatted).strip()
            tokens = formatted.split()
            numOperators: int = 0
            numOperands: int = 0

            for token in tokens:
                if isOperator(token):
                    numOperators += 1
                elif re.search(r'[-+]?[A-Za-z0-9]+', token):
                    numOperands += 1

            if numOperators == 0:
                raise ValueError('No arithmetic operators detected')

            if numOperators >= numOperands:
                raise ValueError('Operator count >= Operand count')
            
            print(f'T = {tokens}')
            return tokens
        
        tokens: list[str] = tokenize(expr)
        operands: list[ExpressionTree._Symbol] = []
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
                operands.append(self._Symbol(token.upper()))

        while operators:
            if operators[-1] == '(':
                raise ValueError('Imbalanced expression found!')
            operands.append(buildNode(operands.pop(), operands.pop(), operators.pop()))

        return operands.pop()
    
    def evaluate(self) -> float | int:
        """
        Performs arithmetic evaluation on the expression tree

        Raises:
        TypeError
            If the operands used for the expression tree is not
            of type float | int.

        Return:
        float | int
            An evaluation of the expression tree
        """
        if not self.solvable:
            raise TypeError('Failed to evaluate the expression tree!')
        
        def eval(root: ExpressionTree._Symbol) -> float | int:
            """
            Performs an arithmetic evaluation starting at 
            the root of the tree

            Parameters:
            root : ExpressionTree._Symbol
                The root of the tree

            Return:
            float | int
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
                        raise ArithmeticError('Cannot divide by 0!')
                    value = left / right
                    return int(value) if value.is_integer() else value
                case '+':
                    value = left + right
                    return int(value) if value.is_integer() else value
                case '-':
                    value = left - right
                    return int(value) if value.is_integer() else value
        
        return eval(self.root)

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
        list[str]
            A list of symbols after performing depth-first search
            on the expression tree.
        """
        if not isinstance(traversal, str):
            raise TypeError('<traversal> must be of type <str>')
        
        def preorder(root: ExpressionTree._Symbol, symbols: list[str] = []) -> list[str]:
            """
            Get a list of symbols after performing preorder traversal on
            the tree

            Parameters:
            root : ExpressionTree._Symbol
                The root of the tree
            symbols : list[str]
                A list of symbols (default is [])
            
            Return:
            list[str]
                A list of symbols
            """
            if not root:
                return []
            
            symbols.append(root.symbol)     
            preorder(root.left)
            preorder(root.right)
            return symbols

        def postorder(root: ExpressionTree._Symbol, symbols: list[str] = []) -> list[str]:
            """
            Get a list of symbols after performing postorder traversal on
            the tree

            Parameters:
            root : ExpressionTree._Symbol
                The root of the tree
            symbols : list[str]
                A list of symbols (default is [])
            
            Return:
            list[str]
                A list of symbols
            """
            if not root:
                return []
            
            postorder(root.left)
            postorder(root.right)
            symbols.append(root.symbol)     
            return symbols
        
        def inorder(root: ExpressionTree._Symbol, symbols: list[str] = []) -> list[str]:
            """
            Get a list of symbols after performing inorder traversal on
            the tree

            Parameters:
            root : ExpressionTree._Symbol
                The root of the tree
            symbols : list[str]
                A list of symbols (default is [])
            
            Return:
            list[str]
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

        return traversals[traversal](self.root)
    
    
    def BFS(self) -> list[list[str]]:
        """
        Retrieves a list of symbols after performing 
        Breadth-First Search (Level-Order) traversal on
        the expression tree.

        Return:
        list[list[str]]
            A list of symbols at each level of the expression tree
            from left to right
        """ 
        def levelOrder(root: ExpressionTree._Symbol) -> list[list[str]]:
            """
            Helper function to retrieve a list of symbols after performing 
            Breadth-First Search (Level-Order) traversal on
            the expression tree.

            Parameters:
            root : ExpressionTree._Symbol
                The root of the expression tree

            Return:
            list[list[str]]
                A list of symbols at each level of the expression tree
                from left to right
            """
            levels: list[list[str]] = []
            queue: list[ExpressionTree._Symbol] = [root]

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

        return levelOrder(self.root)
    
    def height(self) -> int:
        """
        Get the height of the expression tree

        Return:
        int
            The height of the expression tree
        """
        def treeHeight(root: ExpressionTree._Symbol) -> int:
            """
            Get the height of a specified root in the 
            expression tree

            Parameters:
            root : ExpressionTree._Symbol
                The root of the expression tree

            Return:
            int
                The height of a specified root in the
                expression tree
            """
            if not root:
                return -1
            
            leftHeight: int = treeHeight(root.left)
            rightHeight: int = treeHeight(root.right)
            return 1 + max(leftHeight, rightHeight)
        
        return treeHeight(self.root)
    
    """
    Print the tree representation of the expression tree
    """
    def print(self) -> None:
        def printTree(root: ExpressionTree._Symbol) -> None:
            if not root:
                return
            
            print(root.symbol)
            printTree(root.left)
            printTree(root.right)

        pass
