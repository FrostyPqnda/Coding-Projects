import sys
from ExpressionTree import ExpressionTree

if __name__ == '__main__':
    expr = sys.argv[1:] if sys.argv[1:] else input('Enter arithmetic expression: ')
    tree = ExpressionTree(expr)
    print(tree)
    tree.print()