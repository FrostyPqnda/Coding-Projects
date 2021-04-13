import java.util.ArrayList;

public class IsPalindromeList
{
    public static void main(String[] args)
    {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        arr.add(1);
        arr.add(2);
        arr.add(2);
        arr.add(1);

        System.out.println(arr);
        System.out.println("Is a palindrome list: " + isPalindrome(arr));
    }

    public static boolean isPalindrome(ArrayList<Integer> arrList)
    {
        boolean palindrome = true;
        for(int i = 0; i <= arrList.size() / 2 && !arrList.isEmpty(); i++)
        {
            if(arrList.get(i) != arrList.get(arrList.size() - i - 1))
            {
                palindrome = false;
            }
        }
        return palindrome;
    }
}