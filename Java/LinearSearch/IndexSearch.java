import java.util.ArrayList;

public class IndexSearch extends LinearSearchAlgorithm
{
    public static void main(String[] args)
    {
        Pokemon salamence = new Pokemon("Salamence", "Dragon", "Flying", "Intimidate", "Moxie", true, true, false, 394, 100);
        Pokemon charizard = new Pokemon("Charizard", "Fire", "Flying", "Solar Power", "Blaze", false, true, false, 360, 100);
        Pokemon lucario = new Pokemon("Lucario", "Fighting", "Steel", "Inner Focus", "Justified", true, true, true, 344, 100);
        Pokemon blastoise = new Pokemon("Blastoise", "Water", "None", "Torrent", "Rain Dish", true, true, true, 362, 100);
        Pokemon gengar = new Pokemon("Gengar", "Ghost", "Poison", "Cursed Body", "Levitate", true, true, false, 324, 100);
        Pokemon infernape = new Pokemon("Infernape", "Fire", "Fighting", "Blaze", "Iron Fist", true, false, false, 356, 100);
        
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
