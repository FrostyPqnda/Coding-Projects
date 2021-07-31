import java.util.ArrayList;

public class PrimeFactor
{
    private int number;
    private ArrayList<Integer> primeList;
    
    // Constructor for the PrimeFactor class
    //
    // Creates a list of possible prime factorization of a number
    public PrimeFactor(int number)
    {
        this.number = number;
        primeList = new ArrayList<Integer>();

        for(int i = 2; i <= number; i++)
        {
            while(number % i == 0)
            {
                primeList.add(i);
                number /= i;
            }
        }
    }
    
    /**
     * 
     * @return primeList
     */
    public ArrayList<Integer> getPrimeList()
    {
        return primeList;
    }

    /**
     * 
     * @return number
     */
    public int getNumber()
    {
        return number;
    }

    // return the object in the String format
    public String toString()
    {
        return primeList.toString();
    }
}