class PascalTriangle:
    # Instantiate the PascalTriangle class
    # Create Pascal's Triangle of size n
    def __init__(self, n: int):
        if n <= 0:
            raise ValueError('n cannot be less than 0!')
        if not isinstance(n, int):
            raise TypeError('n must be an integer value')
        
        self.__pascal = [[] for _ in range(n)]
        for i in range(n):
            self.__pascal[i] = [0 for _ in range(i + 1)]

        for i in range(n):
            for j in range(i + 1):
                self.__pascal[i][j] = 1 if j == 0 or j == i else self.__pascal[i - 1][j - 1] + self.__pascal[i - 1][j]

    # If n is not defined: return the entire Pascal's Triangle.
    # Otherwise, return specified row of the Pascal's Triangle.
    # 
    # Uses 1-based indexing
    def get(self, n: int = None) -> list:
        if n is None:
            return self.__pascal
        
        if n <= 0:
            raise ValueError('n cannot be less than 0!')
        
        return self.__pascal[n - 1] 
    
    # Add k rows to the Pascal's Triangle
    def add(self, k: int):
        if k <= 0:
            raise ValueError('k must be greater than 0')

        n = len(self.__pascal)
        for i in range(n, n + k):
            self.__pascal.append([0 for _ in range(i + 1)])

        for i in range(n, n + k):
            for j in range(i + 1):
                self.__pascal[i][j] = 1 if j == 0 or j == i else self.__pascal[i - 1][j - 1] + self.__pascal[i - 1][j]
    
    # Subtract k rows from the Pascal's Triangle
    def subtract(self, k: int):
        if k <= 0:
            raise ValueError('k must be greater than 0')
        
        n = len(self.__pascal)
        if n - k <= 0:
            raise ValueError('The updated size cannot be less than 1')
        
        for i in range(k):
            self.__pascal.pop()

    # Print the triangle
    def print(self) -> None:
        n = len(self.__pascal)
        for i in range(n):
            print(' ' * (n - i), end = '')
            for j in range(i + 1):
                print(self.__pascal[i][j], end = ' ')
            print()

    # Add another PascalTriangle object to the current PascalTriangle
    def __add__(self, other):
        if not isinstance(other, PascalTriangle):
            raise ValueError(f'other is of type {type(other)}')
        
        for row in other.__pascal:
            if row not in self.__pascal:
                self.__pascal.append(row)

        return self
    
    # Return PascalTriangle as a str object
    def __str__(self) -> str:
        return f'{self.__pascal}'
    
    # Return the represenation of the PascalTriangle object
    def __repr__(self) -> str:
        return f'PascalTriangle(n = {len(self.__pascal)})'

if __name__ == '__main__':
    n = int(input('Enter number of rows: '))
    pascal = PascalTriangle(n)
    print(f'{pascal}')


