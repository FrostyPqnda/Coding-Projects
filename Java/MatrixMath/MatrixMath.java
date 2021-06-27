public class MatrixMath
{
    private int[][] matA;
    private int[][] matB;

    public MatrixMath(int[][] matA, int[][] matB)
    {
        this.matA = matA;
        this.matB = matB;
    }

    /**
     * 
     * @return the added matrix of matA and matB
     * 
     * Precondition: matA dimension == matB dimension
     * 
     * If the dimensions between the two matrix are different,
     * return an empty matrix
     */
    public int[][] addMatrices()
    {
        int[][] mat = new int[0][0];

        if(matA.length == matB.length && matA[0].length == matB[0].length)
        {
            mat = new int[matA.length][matA[0].length];
            for(int r = 0; r < matA.length; r++)
            {
                for(int c = 0; c < matA[r].length; c++)
                {
                    mat[r][c] = (matA[r][c] + matB[r][c]);
                }
            }
        }

        return mat;
    }

    /**
     * 
     * @return the subtracted matrix of matA and matB
     * 
     * Precondition: matA dimension == matB dimension
     * 
     * If the dimensions between the two matrix are different,
     * return an empty matrix
     */
    public int[][] subtractMatrices()
    {
        int[][] mat = new int[0][0];

        if(matA.length == matB.length && matA[0].length == matB[0].length)
        {
            mat = new int[matA.length][matA[0].length];
            for(int r = 0; r < matA.length; r++)
            {
                for(int c = 0; c < matA[r].length; c++)
                {
                    mat[r][c] = (matA[r][c] - matB[r][c]);
                }
            }
        }

        return mat;
    }

    /**
     * 
     * @return the multiplicative matrix of matA and matB
     * 
     * Precondition: # of column in matA == # of row of matB
     * 
     * If the precondition is not satisfied, return an empty 2D array
     */
    public int[][] multiplyMatrices()
    {
        int[][] mat = new int[0][0];
        
        if(matA[0].length == matB.length)
        {
            mat = new int[matA.length][matB[0].length];
            int sum = 0;

            for(int r = 0; r < mat.length; r++)
            {
                for(int c = 0; c < mat[r].length; c++)
                {
                    for(int i = 0; i < matA[0].length; i++)
                    {
                        sum += matA[r][i] * matB[i][c];
                    }
                    mat[r][c] = sum;
                    sum = 0;
                }
            }
        }

        return mat;
    }
}