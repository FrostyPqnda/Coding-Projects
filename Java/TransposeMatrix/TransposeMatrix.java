import java.util.*;

class TransposeMatrix {
    static int[][] mat = { 
        { 13, 51, 32, 89, 39 }, 
        { 33, 18, 44, 90, 73 }, 
        { 34, 16, 47, 46, 63 } 
    };

    public static void main(String[] args) {
        System.out.println("Orignal");
        print2D(mat);

        System.out.println("\nTranspose");
        print2D(transpose(mat));
    }

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

    static void print2D(int[][] mat) 
    {
        for (int[] row : mat) 
        {
            System.out.println(Arrays.toString(row));
        }
    }
}