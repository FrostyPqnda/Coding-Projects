from typing import Union, Sequence


class Matrix:
    def __init__(self, board: list[float] = [[]]):
        self.mat = board

    def isSquare(self):
        return len(self.mat) == len(self.mat[0])

    def getMatrix(self):
        return self.mat

    def transpose(self):
        a = self.mat
        col = len(a[0])
        row = len(a)
        T = [[None for _ in range(row)] for _ in range(col)]
        r, c = 0, 0

        for j in range(col):
            for i in range(row):
                T[r][c] = a[i][j]
                c += 1
                if c == row:
                    c = 0
                    r += 1

        return Matrix(T)

    # Calculates the determinant. Code taken from [https://www.geeksforgeeks.org/determinant-of-a-matrix/]
    def det(self) -> int:
        if not self.isSquare():
            return None
        
        a = self.mat
        temp = [0] * len(a)
        total = 1
        det = 1

        for i in range(len(a)):
            index = i
            while index < len(a) and a[index][i] == 0:
                index += 1

            if index == len(a):
                continue

            if index != i:
                for j in range(len(a)):
                    a[index][j], a[i][j] = a[i][j], a[index][j]

                det *= int(pow(-1, index - i))

            for j in range(len(a)):
                temp[j] = a[i][j]

            for j in range(i + 1, len(a)):
                n = temp[i]
                m = a[j][i]

                for k in range(len(a)):
                    a[j][k] = (n * a[j][k]) - (m * temp[k])
                
                total *= n
        
        for i in range(len(a)):
            det *= a[i][i]

        return int(det / total)

    # Returns the sum of two matrices
    def __add__(self, other):
        a = self.mat
        b = other.mat
        if len(a) == len(b) and len(a[0]) == len(b[0]):
            res = [[0 for _ in range(len(a))] for _ in range(len(a[0]))]
            for r in range(len(res)):
                for c in range(len(res[r])):
                    res[r][c] = (a[r][c] + b[r][c])

            return Matrix(res) 
        else:
            return None

    # Returns the difference of two matrices
    def __sub__(self, other):
        a = self.mat
        b = other.mat
        if len(a) == len(b) and len(a[0]) == len(b[0]):
            res = [[0 for _ in range(len(a))] for _ in range(len(a[0]))]
            for r in range(len(res)):
                for c in range(len(res[r])):
                    res[r][c] = (a[r][c] - b[r][c])

            return Matrix(res) 
        else:
            return None
    
    # Returns the product of two matrices
    def __mul__(self, other):
        a = self.mat
        b = other.mat

        if len(a[0]) == len(b):
            res = [[0 for _ in range(len(b[0]))] for _ in range(len(a))]
            sum = 0

            for r in range(len(res)):
                for c in range(len(res[r])):
                    for i in range(len(a[0])):
                        sum += (a[r][i] * b[i][c])
                    res[r][c] = sum
                    sum = 0

            return Matrix(res)
        else:
            return None

    def __str__(self):
        res = ''

        for arr in self.mat:
            res += str(arr) + '\n'

        return res.replace(',', ' ')[:len(res) - 1]


board = [
    [45]
]

mat = [
    [4,5,6],
    [7,8,9],
    [1,2,5]
]

a = Matrix(mat)
b = Matrix(mat)
c = a * b
print(a)