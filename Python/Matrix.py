from __future__ import annotations
from typing import List, Union
import copy

class Matrix:
    # Constructor for the Matrix class
    def __init__(self, board: List[List[float]]):
        if isinstance(board[0], (float, int)):
            self.mat = [[0 for _ in range(1)] for _ in range(len(board))]
            for i in range(len(board)):
                self.mat[i][0] = board[i]
        else:
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

        det = det / total

        if det.is_integer():
            det = round(det)

        return det

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
    
    # Checks if the matrix is symmetric.
    # That is A = the transpose of A 
    def isSymmetric(self):
        return self == self.transpose()

    # Checks if matrix is skew-symmetric. A = -A^T
    def isSkew(self):
        if not self.isSquare():
            return False
        
        skew = copy.deepcopy(self.transpose().scalar_mult(-1)).getMatrix()
        A = self.getMatrix()

        for i in range(len(A)):
            for j in range(len(A)):
                if A[i][j] != skew[i][j]:
                    return False

        return True

    # Checks if the matrix is idempotent. A² = A
    def isIdempotent(self):
        if not self.isSquare():
            return False

        A = self.getMatrix()
        A_Squared = copy.deepcopy(self ** 2).getMatrix()

        for i in range(len(A)):
            for j in range(len(A)):
                if A[i][j] != A_Squared[i][j]:
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

    # Returns the LU decomposition of the matrix as a dictonary. Code taken from
    # [https://www.geeksforgeeks.org/doolittle-algorithm-lu-decomposition/]
    def LU_decomp(self):
        if not self.isSquare():
            return None

        A = self.getMatrix()
        upper = [[0 for _ in range(len(A))] for _ in range(len(A))]
        lower = [[0 for _ in range(len(A))] for _ in range(len(A))]

        for i in range(len(A)):
            for j in range(i, len(A)):
                sum = 0
                for k in range(i):
                    sum += (lower[i][k] * upper[k][j])
                upper[i][j] = A[i][j] - sum

            for j in range(i, len(A)):
                if i == j:
                    lower[i][i] = 1
                else:
                    sum = 0
                    for k in range(i):
                        sum += (lower[j][k] * upper[k][i])
                    
                    lower[j][i] = (A[j][i] - sum) / upper[i][i]
                    if lower[j][i].is_integer():
                        lower[j][i] = round(lower[j][i])

        dict = {
            'L': Matrix(lower),
            'U': Matrix(upper)
        }
        return dict

    # Return the LDU decomposition of the matrix
    def LDU_decomp(self):
        lower = copy.deepcopy(self.lower())
        diagonal = copy.deepcopy(self.diagonal())
        upper = copy.deepcopy(self.upper())

        upperMat = upper.getMatrix()

        for i in range(len(upperMat)):
            for j in range(len(upperMat)):
                upperMat[i][j] = (upperMat[i][j] / diagonal.getMatrix()[i][i])
                if upperMat[i][j].is_integer():
                    upperMat[i][j] = round(upperMat[i][j])

        newUpper = Matrix(upperMat)
        dict = {
            'L': lower,
            'D': diagonal,
            'U': newUpper
        }
        return dict

    # Return the upper triangular form of the matrix
    def upper(self):
        dict = self.LU_decomp()
        return dict['U']

    # Return the lower triangular form of the matrix
    def lower(self):
        dict = self.LU_decomp()
        return dict['L']

    def diagonal(self):
        U = copy.deepcopy(self.upper().getMatrix())
        D = [[0 for _ in range(len(U))] for _ in range(len(U))]

        for i in range(len(U)):
            for j in range(len(U)):
                if i == j:
                    D[i][j] = U[i][j]

        return Matrix(D)

    # Solves the linear system of equation. inv(A) * b = x
    # Only works if matrix A is invertible (as of now).
    def solve(self, b: list):
        if self.inv() == None:
            return None

        inv = copy.deepcopy(~self)
        bMat = Matrix(b)

        return inv * bMat

    # Swaps two rows in the Matrix. Done via 1-based indexing
    def row_swap(self, row_i: int, row_j: int):
        if not all(isinstance(k, int) for k in [row_i, row_j]):
            return 'Invalid type'

        A = copy.deepcopy(self.getMatrix())
        if row_i >= row_j:
            row_i, row_j = row_j, row_i


        if row_i - 1 < 0 or row_j - 1 > len(A) - 1:
            return 'Invalid row'
        
        A[row_i - 1], A[row_j - 1] = A[row_j - 1], A[row_i - 1]
        return Matrix(A)
    
    # Swaps two columns in the Matrix. Done via 1-based indexing
    def col_swap(self, col_i: int, col_j: int):
        if not all(isinstance(k, int) for k in [col_i, col_j]):
            return 'Invalid type'

        A = copy.deepcopy(self.getMatrix())

        if col_i >= col_j:
            col_i, col_j = col_j, col_i

        if col_i - 1 < 0 or col_j - 1 > len(A[0]) - 1:
            return 'Invalid column'

        for i in range(len(A)):
            A[i][col_i - 1], A[i][col_j - 1] = A[i][col_j - 1], A[i][col_i - 1]

        return Matrix(A)

    # Swaps the rows/columns of a matrix depending on the type given (r, c)
    def swap(self, i: int, j: int, type: str):
        if type[0].lower() != ('r' or 'c'):
            return 'Invalid type'

        if type[0].lower() == 'r':
            return self.row_swap(i, j)
        else:
            return self.col_swap(i, j)


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

        A = self.getMatrix()
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

    # Returns the value at the specified indices.
    # Done via 1-based indexing
    def get(self, i: int, j: int):
        if not all(isinstance(k, int) for k in [i, j]):
            return 'Invalid type'

        A = self.getMatrix()

        if i - 1 < 0 or i - 1 > len(A) - 1:
            return 'Invalid row'

        if j - 1 < 0 or j - 1 > len(A[0]) - 1:
            return 'Invalid column'

        return A[i - 1][j - 1]

    def set(self, i: int, j: int, num: Union[int, float]):
        if not all(isinstance(k, int) for k in [i, j]) or not isinstance(num, (int, float)):
            print('Invalid type')
            return

        A = self.getMatrix()

        if i - 1 < 0 or i - 1 > len(A) - 1:
            print('Invalid type')
            return

        if j - 1 < 0 or j - 1 > len(A[0]) - 1:
            print('Invalid type')
            return

        A[i - 1][j - 1] = num

    def getRow(self, row: int):
        if not isinstance(row, int):
            return 'Invalid type'

        A = self.getMatrix()

        if row - 1 < 0 or row - 1 > len(A) - 1:
            return 'Invalid row'

        return A[row - 1]

    def setRow(self, row: int, newRow: list):
        A = self.getMatrix()

        if len(newRow) != len(A[0]):
            print('Invalid size')
            return

        if not isinstance(row, int) or not isinstance(newRow, list):
            print('Invalid type')
            return

        if not all(isinstance(k, (int, float)) for k in newRow):
            print('Invalid data')
            return
    
        if row - 1 < 0 or row - 1 > len(A) - 1:
            print('Invalid row')
            return

        
        A[row - 1] = newRow        

    def getColumn(self, col: int):
        if not isinstance(col, int):
            return 'Invalid type'

        A = self.getMatrix()

        if col - 1 < 0 or col - 1 > len(A[0]) - 1:
            return 'Invalid column'

        column = []

        for i in range(len(A)):
            column.append(A[i][col - 1])

        return column
    
    def setColumn(self, col: int, newCol: list):
        A = self.getMatrix()

        if len(newCol) != len(A):
            print('Invalid size')
            return

        if not isinstance(col, int) or not isinstance(newCol, list):
            print('Invalid type')
            return

        if not all(isinstance(k, (int, float)) for k in newCol):
            print('Invalid data')
            return
    
        if col - 1 < 0 or col - 1 > len(A) - 1:
            print('Invalid column')

        for i in range(len(A)):
            A[i][col - 1] = newCol[i]

    def getDiagonal(self):
        if not self.isSquare():
            return 'Not a square matrix'

        A = self.getMatrix()
        diag = []

        for i in range(len(A)):
            diag.append(A[i][i])

        return diag

    def setDiagonal(self, newDiag: list):
        A = self.getMatrix()
        if not self.isSquare() or len(A) != len(newDiag):
            print('Invalid dimension/size')
            return

        if not all(isinstance(k, (int, float)) for k in newDiag):
            print('Invalid type')
            return

        for i, num in enumerate(newDiag):
            A[i][i] = num

    def getDimension(self):
        A = self.getMatrix()
        return f'Dim = {len(A)} x {len(A[0])}'

    def update(self, row: int, col: int):
        if not all(isinstance(k, int) for k in [row, col]):
            return 'Invalid data'

        A = self.getMatrix()
        A_list = [k for sub in A for k in sub]
        k = 0

        if (row * col) != (len(A) * len(A[0])):
            return 'Invalid dimension'

        B = [[0 for _ in range(col)] for _ in range(row)]
        
        for i in range(len(B)):
            for j in range(len(B[0])):
                B[i][j] = A_list[k]
                k += 1

        return Matrix(B)

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
                    sum = round(sum, 1)
                    if float(sum).is_integer():
                        sum = round(sum)
                    
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

        if exp == 1:
            return self

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

    def __eq__(self, other: Matrix):
        if not isinstance(other, Matrix):
            return False

        A = self.getMatrix()
        B = other.getMatrix()

        if len(A) != len(B) or len(A[0]) != len(B[0]):
            return False

        for i in range(len(A)):
            for j in range(len(B)):
                if A[i][j] != B[i][j]:
                    return False

        return True

    # Returns the Object representation of the Matrix class
    def __repr__(self):
        return f'Matrix("{self.mat}")'

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
        [1, 0, 2],
        [2, 1, 4],
        [5, 5, 9],
    ]

    print(~Matrix(A))