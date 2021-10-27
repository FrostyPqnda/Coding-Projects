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
        
        // Create a new ArrayList 
        ArrayList<Integer> revList = new ArrayList<Integer>();
        
        // Copy elements of the old ArrayList to the new ArrayList
        // in reverse order.
        for(int i = arrList.size() - 1; i >= 0; i--) 
        {
            revList.add(arrList.get(i));
        }

        // Check if the two ArrayList are the same
        for(int i = 0; i < arrList.size(); i++) 
        {
            if(revList.get(i) != arrList.get(i)) 
            {
                isPalindromeList = false;
            }
        }

        return isPalindromeList;
    }
}