/**
 * The TransposeMatrix program converts a given matrix
 * into its transposed matrix in which all elements of
 * a row in the original matrix becomes a column in the
 * new matrix.
 * 
 * @author Brian Pham
 * @version 1.0
 * @since 2022-10-5
 */
import java.util.Arrays;

class TransposeMatrix {
    static int[][] mat = { 
        { 13, 51, 32, 89, 39 }, 
        { 33, 18, 44, 90, 73 }, 
        { 34, 16, 47, 46, 63 } 
    };

    static int[][] A = {
        { 1, 2, 3 },
        { 2, 4, 5 },
        { 3, 5, 8 }
    };

    public static void main(String[] args) {
        System.out.println("Orignal");
        print2D(mat);

        System.out.println("\nTranspose");
        print2D(transpose(mat));

        System.out.println("\nSymmetric? " + isSymmetric(A, transpose(mat)));
    }

    /**
     * @param mat A matrix to be transposed
     * @return A transposed matrix
     * 
     * Example:
     * 
     *     [13, 51, 32, 89, 39]
     * A = [33, 18, 44, 90, 73] 
     *     [34, 16, 47, 46, 63]
     * 
     *       [13, 33, 34]
     *       [51, 18, 16]
     * A^T = [32, 44, 47]
     *       [89, 90, 46]
     *       [39, 73, 63]
     */
    static int[][] transpose(int[][] mat) 
    {
        int colSize = mat[0].length;
        int rowSize = mat.length;
        int[][] T = new int[colSize][rowSize];
        int r = 0, c = 0;

        for(int col = 0; col < colSize; col++) 
        {
            for(int row = 0; row < rowSize; row++) 
            {
                T[r][c] = mat[row][col];
                c++;
                if(c == rowSize) 
                {
                    c = 0;
                    r++;
                }
            }
        }
        return T;
    }

    /**
     * @param A Original matrix
     * @param B The tranposed matrix of A
     * @return If the matrix is symmetric
     * 
     * A matrix is symmetric if its transposed
     * matrix is equal to the original matrix.
     */
    static boolean isSymmetric(int[][] A, int[][] B) {
        int A_rowSize = A.length, B_rowSize = B.length;
        int A_colSize = A[0].length, B_colSize = B[0].length;
        boolean same = true;
        
        // Check if matrices A and B are square matrices
        if((A_rowSize != A_colSize) || (B_rowSize != B_colSize)) {
            return false;
        }
        
        // Check if matrices A and B are the same dimension
        if((A_rowSize != B_rowSize) || (A_colSize != B_colSize)) {
            return false;
        }
        
        // Check if matrix A = matrix B
        for(int i = 0; i < A_rowSize; i++) {
            for(int j = 0; j < A_colSize; j++) {
                if(A[i][j] != B[i][j]) {
                    same = false;
                    break;
                }
            }
        }

        return same;
    }

    // Utility function for printing the
    // 2D Array
    static void print2D(int[][] mat) 
    {
        for (int[] row : mat) 
        {
            System.out.println(Arrays.toString(row));
        }
    }
}