import java.util.Random;

public class Pokemon 
{
    String name;
    String typeOne;
    String typeTwo;
    String ability;
    String hiddenAbility;
    boolean shiny;
    boolean canMegaEvolve;
    boolean canGigantamax;
    int hp;
    int level;
    Random rand = new Random(12345);
    int randHP = rand.nextInt(714) + 1;
    int randLVL = rand.nextInt(100) + 1;

    public Pokemon(String name, String typeOne, String typeTwo, String ability, String hiddenAbility, boolean shiny, boolean canMegaEvolve, boolean canGigantamax, int hp, int level)
    {
        this.name = name;
        this.typeOne = typeOne;
        this.typeTwo = typeTwo;
        this.ability = ability;
        this.hiddenAbility = hiddenAbility;
        this.shiny = shiny;
        this.canMegaEvolve = canMegaEvolve;
        this.hp = hp;
        this.level = level;

        if(typeTwo.equalsIgnoreCase("None") || typeTwo.equals(" ") || typeTwo.equals("")) typeTwo = "None";
        if(hiddenAbility.equalsIgnoreCase("None") || hiddenAbility.equals(" ") || hiddenAbility.equals("")) hiddenAbility = "None";
        if(hp < 0 || hp > 714) this.hp = randHP;
        if(level < 0 || level > 100) this.level = randLVL;
    }

    public Pokemon()
    {
        name = "MissingNo.";
        typeOne = "Null";
        typeTwo = "Null";
        ability = "Null";
        hiddenAbility = "Null";
        shiny = false;
        canMegaEvolve = false;
        canGigantamax = false;
        hp = 0;
        level = 0;
    }

    public String getName()
    {
        return name;
    }
    public String getTypeOne()
    {
        return typeOne;
    }
    public String getTypeTwo()
    {
        return typeTwo;
    }
    public String getAbility()
    {
        return ability;
    }
    public String getHiddenAbility()
    {
        return hiddenAbility;
    }
    public boolean getShiny()
    {
        return shiny;
    }
    public boolean getCanMegaEvolve()
    {
        return canMegaEvolve;
    }
    public boolean getCanGigantamax()
    {
        return canGigantamax;
    }
    public int getHP()
    {
        return hp;
    }
    public int getLevel()
    {
        return level;
    }

    public boolean equals(Pokemon pkmn)
    {
        boolean sameName = name == pkmn.getName();
        boolean sameTypeOne = typeOne == pkmn.getTypeOne();
        boolean sameTypeTwo = typeTwo == pkmn.getTypeTwo();
        boolean sameAbility = ability == pkmn.getAbility();
        boolean sameHiddenAbility = hiddenAbility == pkmn.getHiddenAbility();
        boolean sameShiny = shiny == pkmn.getShiny();
        boolean sameHP = hp == pkmn.getHP();
        boolean sameMegaEvolve = canMegaEvolve == pkmn.getCanMegaEvolve();
        boolean sameLevel = level == pkmn.getLevel();
        boolean sameGigantamx = canGigantamax == pkmn.getCanGigantamax();
        return sameName && sameTypeOne && sameTypeTwo && sameAbility && sameHiddenAbility && sameShiny && sameMegaEvolve && sameHP && sameLevel && sameGigantamx;
    }

    /**
     * Pokemon: 
     * Type 1: 
     * Type 2:
     * Ability:
     * Hidden Ability: 
     * Shiny?
     * HP: 
     */
    public String toString()
    {
        return "Pokemon: " + name + "\nType 1: " + typeOne + "\nType 2: " + typeTwo + "\nAbility: " + ability + "\nHidden Ability: " + hiddenAbility + "\nShiny? " + shiny + "\nMega Evolution? " + canMegaEvolve + "\nGigantamax Factor? " + canGigantamax + "\nHP: " + hp + "\nLevel: " + level;
    }
}
