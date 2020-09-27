import java.util.ArrayList;
import java.util.Random;

public class IndexSearch extends LinearSearchAlgorithm
{
    public static void main(String[] args)
    {
        Random rand = new Random(12345);
        /* Currently not working. Not generating random value when using these two variables. */
        //int randHP = rand.nextInt(714) + 1; 
        //int randLVL = rand.nextInt(100) + 1;

        Pokemon salamence = new Pokemon("Salamence", "Dragon", "Flying", "Intimidate", "Moxie", true, true, false, ((int)(Math.random() * 714) + 1), rand.nextInt(100) + 1);
        Pokemon charizard = new Pokemon("Charizard", "Fire", "Flying", "Solar Power", "Blaze", false, true, false, ((int)(Math.random() * 714) + 1), rand.nextInt(100) + 1);
        Pokemon lucario = new Pokemon("Lucario", "Fighting", "Steel", "Inner Focus", "Justified", true, true, true, ((int)(Math.random() * 714) + 1), rand.nextInt(100) + 1);
        Pokemon blastoise = new Pokemon("Blastoise", "Water", "None", "Torrent", "Rain Dish", true, true, true, ((int)(Math.random() * 714) + 1), rand.nextInt(100) + 1);
        Pokemon gengar = new Pokemon("Gengar", "Ghost", "Poison", "Cursed Body", "Levitate", true, true, false, ((int)(Math.random() * 714) + 1), rand.nextInt(100) + 1);
        Pokemon infernape = new Pokemon("Infernape", "Fire", "Fighting", "Blaze", "Iron Fist", true, false, false, ((int)(Math.random() * 714) + 1), rand.nextInt(100) + 1);
        
        ArrayList<Pokemon> pokemon = new ArrayList<Pokemon>();
        pokemon.add(salamence);
        pokemon.add(charizard);
        pokemon.add(lucario);
        pokemon.add(blastoise);
        pokemon.add(gengar);
        pokemon.add(infernape);
        
        int index = arrayListSearch(pokemon, salamence);

        System.out.println("\n"+ salamence);
        System.out.println("\nFound at index: " + index);
        
    }
}
