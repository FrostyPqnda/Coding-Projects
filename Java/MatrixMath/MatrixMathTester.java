public class MatrixMathTester 
{
    public static void main(String[] args)
    {
        int[][] a = {
            {3, -2, -1},
            {1, 0, 1}
        };

        int[][] b = {
            {1, 4},
            {-2, 3},
            {-2, 3}
        };

        MatrixMath mat = new MatrixMath(a, b);
        int[][] m = mat.multiplyMatrices();

        for(int[] row : m)
        {
            for(int item : row)
            {
                System.out.print(item + " ");
            }
            System.out.println();
        }
    }    
}
