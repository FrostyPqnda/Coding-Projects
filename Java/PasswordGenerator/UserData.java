import java.util.Scanner;
import java.util.HashMap;
import java.util.InputMismatchException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
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

        System.out.print("Enter an username: ");
        String name = scan.nextLine();
        
        System.out.print("Enter a length: ");
        int length = scan.nextInt();
        String password = generatePassword(length);
        
        
        scan.close();
        
        try {
            SecretKey secretKey = generateKey("AES");
            Cipher cipher = Cipher.getInstance("AES");

            byte[] encryptedPassword = encryptString(password, secretKey, cipher);
            //byte[] encryptedUsername = encryptString(name, secretKey, cipher);
            writeToPasswordList(name, encryptedPassword.toString());

            //String decryptedData = decryptString(encryptedData, secretKey, cipher);
            //System.out.println("Decrypted: " + decryptedData);
        } catch(Exception e) {
            System.out.println(e);
        }

        readPasswordList();
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

    static SecretKey generateKey(String encryptionType) throws Exception
    {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(encryptionType);
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey;
    }

    static byte[] encryptString(String dataToEncrypt, SecretKey secretKey, Cipher cipher) throws Exception
    {
        byte[] text = dataToEncrypt.getBytes("UTF-8");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(text);
    }

    static String decryptString(byte[] dataToDecrypt, SecretKey secretKey, Cipher cipher) throws Exception
    {
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedText = cipher.doFinal(dataToDecrypt);
        return new String(decryptedText);
    }
}
