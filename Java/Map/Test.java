public class Test {
    public static void main(String[] args) {
        Map<Character, Integer> map = new HashMap<>();
        for(char c = 65; c <= 90; c++) {
            char key = (char)((Math.random() * 26) + 'a');
            int value = (int)(Math.random() * c) + 65;
            map.put(key, value);
        }

        System.out.println(map.keySet());
    }
}
