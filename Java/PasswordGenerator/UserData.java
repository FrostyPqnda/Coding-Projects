import java.util.Scanner;
import java.util.HashMap;
import java.util.InputMismatchException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class UserData extends PasswordGenerator 
{    
    public static void main(String[] args) throws InputMismatchException
    {
        Scanner scan = new Scanner(System.in);
        PasswordSecurity ps = new PasswordSecurity();

        System.out.print("Enter an username: ");
        String name = scan.nextLine();
        
        System.out.print("Enter a length: ");
        int length = scan.nextInt();
        String password = generatePassword(length);
        
        try {
            SecretKey secretKey = ps.generateKey("AES");
            Cipher cipher = Cipher.getInstance("AES");

            byte[] encryptedPassword = ps.encryptString(password, secretKey, cipher);
            //byte[] encryptedUsername = encryptString(name, secretKey, cipher);
            writeToPasswordList(name, encryptedPassword.toString());

            //String decryptedData = decryptString(encryptedData, secretKey, cipher);
            //System.out.println("Decrypted: " + decryptedData);
        } catch(Exception e) {
            System.out.println(e);
        }

        readPasswordList();
        scan.close();
    }

    static void writeToPasswordList(String userKey, String userPassword)
    {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("PasswordList.txt", true));
            bw.write(userKey + ": " + userPassword);
            bw.newLine();
            bw.close();
            System.out.println("Content successfully written to PasswordList.txt");
        } catch(IOException io) {
            System.out.println("An error occured!");
			io.printStackTrace();
        }
    }

    static void readPasswordList()
    {
        try {
            HashMap<String, String> hm = new HashMap<String, String>();
            String line;

            BufferedReader br = new BufferedReader(new FileReader("PasswordList.txt"));
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(": ", 2);
                if(parts.length >= 2) {
                    String key = parts[0];
                    String value = parts[1];
                    hm.put(key, value);
                } else {
                    System.out.println("Ignoring line: " + line);
                }
            }

            for(String key : hm.keySet())
                System.out.println(key + ": " + hm.get(key));
            
            br.close();

        } catch(IOException io) {
            System.out.println("An error occured!!");
            io.printStackTrace();
        }
    }

    
}
