import java.util.ArrayList;

public class PrimeFactor
{
    private int number;
    private ArrayList<Integer> primeList;

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

    public ArrayList<Integer> getPrimeList()
    {
        return primeList;
    }

    public int getNumber()
    {
        return number;
    }

    public String toString()
    {
        return primeList.toString();
    }
}