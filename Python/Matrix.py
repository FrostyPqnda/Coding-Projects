from __future__ import annotations
from typing import List
import copy

class Matrix:
    # Constructor for the Matrix class
    def __init__(self, board: List[List[float]]):
        self.mat = board

        
    # Checks if the matrix is a square matrix.
    # No. of rows = No. of columns.
    def isSquare(self):
        return len(self.getMatrix()) == len(self.getMatrix()[0])

    # Return the matrix as a 2D list
    def getMatrix(self):
        return self.mat

    # Returns the transposed matrix of the original matrix.
    # A transposed matrix is matrix in which the rows and 
    # columns of the matrix are swapped with each other.
    def transpose(self):
        a = self.getMatrix()
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

    def isSymmetric(self):
        if not self.isSquare():
            return False

        A = self.getMatrix()
        B = A.transpose().getMatrix()

        for i in range(len(A)):
            for j in range(len(B)):
                if A[i][j] != B[i][j]:
                    return False

        return True

    # Calculates the determinant. Code taken from [https://www.geeksforgeeks.org/determinant-of-a-matrix/]
    def det(self) -> int:
        if not self.isSquare():
            return None
        
        a = copy.deepcopy(self.getMatrix())
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

    # Checks if the matrix is the identity matrix
    def isIdentity(self):
        if not self.isSquare():
            return False
        A = self.getMatrix()
        for i in range(len(A)):
            for j in range(len(A)):
                if i == j:
                    if A[i][j] != 1:
                        return False
                else:
                    if A[i][j] != 0:
                        return False
        
        return True

    # Checks if the matrix is the zero matrix
    def isZero(self):
        A = self.getMatrix()

        for i in range(len(A)):
            for j in range(len(A[i])):
                if A[i][j] != 0:
                    return False
        
        return True

    def scalar_mult(self, k):
        if not isinstance(k, (int, float)):
            return None

        A = copy.deepcopy(self.getMatrix())

        for i in range(len(A)):
            for j in range(len(A[i])):
                A[i][j] = k * A[i][j]

        return Matrix(A)


    # Swaps two rows in the Matrix. Done via 1-based indexing
    def row_swap(self, row_i: int, row_j: int):
        if not all(isinstance(k, int) for k in [row_i, row_j]):
            return 'Invalid type'

        if row_i >= row_j:
            row_i, row_j = row_j, row_i

        if row_i - 1 < 0 or row_j - 1 > 2:
            return 'Invalid row'

        A = copy.deepcopy(self.getMatrix())
        A[row_i - 1], A[row_j - 1] = A[row_j - 1], A[row_i - 1]
        return Matrix(A)

    # Returns the N - 1 submatrix of the Matrix
    # given the row and col indices
    def cof_sub(self, p: int, q: int):
        if not self.isSquare():
            return 'Not a square matrix'
        
        i = 0 
        j = 0
        A = self.getMatrix()
        cof = [[0 for _ in range(len(A) - 1)] for _ in range(len(A) - 1)]

        for r in range(len(A)):
            for c in range(len(A)):
                if r != p and c != q:
                    cof[i][j] = A[r][c]
                    j += 1

                    if j == len(A) - 1:
                        j = 0
                        i += 1

        return Matrix(cof)

    # Returns the cofactor matrix of the matrix
    def cof(self):
        if not self.isSquare():
            return 'Not a square matrix'

        A = self.getMatrix()
        cofMat = [[0 for _ in range(len(A))] for _ in range(len(A))]

        sign = 1
        for i in range(len(A)):
            for j in range(len(A)):
                cof = self.cof_sub(i, j)
                sign = [1, -1][(i + j) % 2]
                cofMat[i][j] = (sign) * cof.det()

        return Matrix(cofMat)
        
    # Returns the transpose of the cofactor matrix
    def adj(self):
        return self.cof().transpose()

    # Returns the inverse of the matrix
    def inv(self):
        det = self.det()
        if not self.isSquare() or det == 0:
            return 'Not invertible'

        A = copy.deepcopy(self.getMatrix())
        inverse = [[0 for _ in range(len(A))] for _ in range(len(A))]
        adj = copy.deepcopy(self.adj().getMatrix())

        for i in range(len(A)):
            for j in range(len(A)):
                inverse[i][j] = ((1/det) * adj[i][j])

                if inverse[i][j].is_integer():
                    inverse[i][j] = int(inverse[i][j])
                else:
                    inverse[i][j] = round(inverse[i][j], 3)

        return Matrix(inverse)
    
    # Returns the projection matrix
    # Might need to modify/fix
    def proj(self):
        A = copy.deepcopy(self)
        B = A.transpose()
        C = B * A

        if C.det() == 0:
            return None

        K = A * ~C
        return K * B
                
    # Returns the sum of two matrices
    def __add__(self, other: Matrix):
        if not isinstance(other, Matrix):
            print('Variable:', other, 'is of type {}'.format(type(other)))
            return None

        a = self.getMatrix()
        b = other.getMatrix()
        if len(a) == len(b) and len(a[0]) == len(b[0]):
            res = [[0 for _ in range(len(a))] for _ in range(len(a[0]))]
            for r in range(len(res)):
                for c in range(len(res[r])):
                    res[r][c] = (a[r][c] + b[r][c])

            return Matrix(res) 
        else:
            return None

    # Return the sum of this matrix and the other matrix
    def __iadd__(self, other: Matrix):
        return self + other

    # Returns the difference of two matrices
    def __sub__(self, other: Matrix):
        if not isinstance(other, Matrix):
            print('Variable:', other, 'is of type {}'.format(type(other)))
            return None

        a = self.getMatrix()
        b = other.getMatrix()
        if len(a) == len(b) and len(a[0]) == len(b[0]):
            res = [[0 for _ in range(len(a))] for _ in range(len(a[0]))]
            for r in range(len(res)):
                for c in range(len(res[r])):
                    res[r][c] = (a[r][c] - b[r][c])

            return Matrix(res) 
        else:
            return None

    # Return the difference of this matrix and the other matrix
    def __isub__(self, other: Matrix):
        return self - other
    
    # Returns the product of two matrices
    def __mul__(self, other: Matrix):
        if not isinstance(other, Matrix):
            print('Variable:', other, 'is of type {}'.format(type(other)))
            return None

        a = self.getMatrix()
        b = other.getMatrix()

        if len(a[0]) == len(b):
            res = [[0 for _ in range(len(b[0]))] for _ in range(len(a))]
            sum = 0

            for r in range(len(res)):
                for c in range(len(res[r])):
                    for i in range(len(a[0])):
                        sum += (a[r][i] * b[i][c])
                    sum = round(sum, 3)
                    if float(sum).is_integer():
                        sum = int(sum)
                    res[r][c] = sum
                    sum = 0

            return Matrix(res)
        else:
            return None

    # Return the product of this matrix and the other matrix
    def __imul__(self, other: Matrix):
        return self * other

    # Returns a matrix times itself N times
    def __pow__(self, exp: int):
        if not isinstance(exp, int):
            print('Variable:', exp, 'is of type {}'.format(type(exp)))
            return None
            
        if exp <= 0:
            return None

        B = copy.deepcopy(self)
        C = copy.deepcopy(B)
        for i in range(exp):
            B = self * self
            B *= C

        return B

    # Returns the inverse of the matrix. Symbol is the ~
    # Ex: ~C
    def __invert__(self):
        return self.inv()

    # Returns the Object representation of the Matrix class
    def __repr__(self):
        return f'Matrix = ({self.getMatrix()})'

    # Returns the string representation of the Matrix class
    def __str__(self):
        res = ''

        for arr in self.getMatrix():
            res += str(arr) + '\n'

        return res.replace(',', ' ')[:len(res) - 1]

# Only called within the file itself. 
# Cannot be called if it is imported.
if __name__ == '__main__':
    A = [
        [1, 5, 6],
        [2, 1, 4],
        [9, 3, 7]
    ]

    B = [
        [0],
        [0],
        [0]
    ]

    C = [
        [0, 1],
        [1, 0]
    ]
    
    P = Matrix(A)
    Q = Matrix(B)
    R = Matrix(C)
    print(P)
    print('\n')
    print(R ** 4)