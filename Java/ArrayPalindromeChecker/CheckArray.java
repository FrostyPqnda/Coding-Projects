public class CheckArray
{
    /**
     * checkPalindrome checks if @param arr 
     * is a palindrome (A sequence reads the same backward as forward).
     * 
     * Code snippet taken from GeeksforGeeks
     * SOURCE: https://www.geeksforgeeks.org/program-to-check-if-an-array-is-palindrome-or-not/
     */
    public static boolean checkPalindrome(int[] arr)
    {
        boolean isPalindrome = true;
        for(int i = 0; i <= arr.length / 2 && arr.length != 0; i++)
            if(arr[i] != arr[arr.length - i - 1])
                isPalindrome = false;
        return isPalindrome;
    }
}