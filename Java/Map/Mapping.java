public class Mapping {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        
        String[] names = {"Old Yeller", "Garfield", "Nemo", "Mr. Nibbles", "Rigby"};
        String[] animals = {"Dog", "Cat", "Fish", "Gecko", "Raccoon"};

        for(int i = 0; i < 5; i++) {
            map.put(names[i], animals[i]);
        }
                
        System.out.println(map);
    }
}
