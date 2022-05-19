public class RotateMatrix 
{
    public static void main(String[] args) 
    {

        int[][] mat = {
            {1,2,3},
            {4,5,6},
            {7,8,9}
        };

        System.out.println("Original");
        print(mat);

        rotate(mat);
        System.out.println("\nNew");
        print(mat);
    }

    /**
     * @param mat
     * 
     * Rotate a matrix by 90 degrees in a 
     * counterclockwise rotation.
     */
    static void rotate(int[][] mat) 
    {
        for(int i = 0; i < mat.length / 2; i++) {
            for(int j = i; j < mat.length - i - 1; j++) {
                int temp = mat[i][j];
                mat[i][j] = mat[j][mat.length - 1 - i];
                mat[j][mat.length - 1 - i] = mat[mat.length - 1 - i][mat.length - 1 - j];
                mat[mat.length - 1 - i][mat.length - 1 - j] = mat[mat.length - 1 - j][i];
                mat[mat.length - 1 - j][i] = temp;
            }
        }
    }

    /**
     * @param mat
     * 
     * Print the matrix
     */
    static void print(int[][] mat) {
        for(int[] arr : mat) {
            for(int item : arr) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
    }
}