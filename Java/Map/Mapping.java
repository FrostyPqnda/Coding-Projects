public class Mapping {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        
        java.util.Scanner scan = new java.util.Scanner(System.in);
        System.out.print("Enter map size: ");
        int size = scan.nextInt();
        
        for(int i = 0; i < size; i++) {
            System.out.print("Enter <String, String> entry pair: ");
            String key = scan.next();
            String value = scan.next();
            map.put(key, value);
        }

        scan.close();
        System.out.println(map);
    }
}
