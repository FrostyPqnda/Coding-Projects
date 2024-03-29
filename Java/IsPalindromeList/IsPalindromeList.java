import java.util.ArrayList;

public class IsPalindromeList
{
    public static void main(String[] args)
    {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(2);
        arr.add(1);

        System.out.println(arr);
        System.out.println("Is a palindrome list: " + isPalindrome(arr));
    }
    
    /**
     * Checks if the given ArrayList is a palindrome.
     * 
     * @param arrList
     * @return True/False statement whether or not the array list is a palindrome
     */
    public static boolean isPalindrome(ArrayList<Integer> arrList)
    {
        boolean isPalindromeList = true;
        
        for(int i = 0; i < arrList.size() / 2; i++) 
        {
            if(arrList.get(i) != arrList.get(arrList.size() - i - 1)) {
                isPalindromeList = false;
            }
        }

        return isPalindromeList;
    }
}