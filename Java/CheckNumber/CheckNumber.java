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
        
        System.out.println(num + " is a " + checkFactor(num));

        scan.close();
    }
    
    /**
     * @param number
     * @return String stating if a number is prime or composite or neither
     */
    static String checkFactor(int number)
    {
        if(number == 0 || number == 1)
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

        if(number > 0)
        {
            for(int i = 1; i <= number; i++)
            {
                if(number % i == 0)
                {
                    factorList.add(i);
                }
            }
        }
        else
        {
            for(int i = number; i <= Math.abs(number); i++)
            {
                if(i == 0) 
                {
                    continue;
                } 
                else 
                {
                    if(number % i == 0) 
                    {
                        factorList.add(i);
                    }
                }
            }
        }

        return factorList;
    }
}