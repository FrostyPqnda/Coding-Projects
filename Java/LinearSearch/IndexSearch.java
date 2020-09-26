import java.util.ArrayList;

public class IndexSearch extends LinearSearchAlgorithm
{
    public static void main(String[] args)
    {
        printExample();
    }

    // Linear Search Algortithm examples using String array and ArrayList
    static void printExample()
    {
        System.out.println("====================================");
        ArrayList<String> animals = new ArrayList<String>();
        animals.add("Dog");
        animals.add("Cat");
        animals.add("Horse");
        animals.add("Penguin");
        animals.add("Lion");

        for(int i = 0; i < animals.size(); i++)
        {
            String value = animals.get(i);
            int index = arrayListSearch(animals, value);

            System.out.println("Animal: '" + value + "' is at index: " + index);
        }
        System.out.println("====================================");
        String[] subjects = {
            "English", "Math", "History", "Science", "Art"
        };

        for(int i = 0; i < subjects.length; i++)
        {
            String value = subjects[i];
            int index = arraySearch(subjects, value);

            System.out.println("Subject: '" + value + "' is at index: " + index);
        }
        System.out.println("====================================");
    }
}
