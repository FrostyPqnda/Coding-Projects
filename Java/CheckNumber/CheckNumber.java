import java.util.*;

/**
 * Java program that checks if a given number
 * is either a prime or composite number.
 */
public class CheckNumber
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int num = scan.nextInt();
        
        System.out.print("\nPossible factors of " + num + ": ");
        System.out.print(getFactor(num));
        System.out.print("\n\n" + num + " is a " + checkFactor(num));

        scan.close();
    }
    
    /**
     * @param number
     * @return String stating if a number is prime or composite
     */
    static String checkFactor(int number)
    {
        if(number == 0)
        {
            return "Neither";
        }

        ArrayList<Integer> factorList = getFactor(number);

        if(factorList.size() > 2)
        {
            return "Composite Number";
        }
        else
        {
            return "Prime Number";
        }
    }

    /**
     * @param number
     * @return An ArrayList of possible factors
     */
    static ArrayList<Integer> getFactor(int number)
    {
        ArrayList<Integer> factorList = new ArrayList<Integer>();

        for(int i = 1; i <= number; i++)
        {
            if(number % i == 0)
            {
                factorList.add(i);
            }
        }

        return factorList;
    }
}