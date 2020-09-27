import java.util.ArrayList;

public class IndexSearch extends LinearSearchAlgorithm
{
    public static void main(String[] args)
    {
        int randHP = ((int)(Math.random() * 714) + 1);
        int randLVL = ((int)(Math.random() * 100) + 1);

        Pokemon salamence = new Pokemon("Salamence", "Dragon", "Flying", "Intimidate", "Moxie", true, true, false, randHP, randLVL);
        Pokemon charizard = new Pokemon("Charizard", "Fire", "Flying", "Solar Power", "Blaze", false, true, false, randHP, randLVL);
        Pokemon lucario = new Pokemon("Lucario", "Fighting", "Steel", "Inner Focus", "Justified", true, true, true, randHP, randLVL);
        Pokemon blastoise = new Pokemon("Blastoise", "Water", "None", "Torrent", "Rain Dish", true, true, true, randHP, randLVL);
        Pokemon gengar = new Pokemon("Gengar", "Ghost", "Poison", "Cursed Body", "Levitate", true, true, false, randHP, randLVL);
        Pokemon infernape = new Pokemon("Infernape", "Fire", "Fighting", "Blaze", "Iron Fist", true, false, false, randHP, randLVL);
        
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
