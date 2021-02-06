import java.util.Scanner;
import java.util.InputMismatchException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.FileWriter;
import java.io.IOException;

public class UserData extends PasswordGenerator 
{    
    public static void main(String[] args) throws InputMismatchException
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a length: ");
        int length = scan.nextInt();
        String password = generatePassword(length);
        System.out.println("Password: " + password);
        
        System.out.print("Enter an username: ");
        String name = scan.nextLine();

        try {
            SecretKey secretKey = generateKey("AES");
            Cipher cipher = Cipher.getInstance("AES");

            byte[] encryptedPassword = encryptString(password, secretKey, cipher);
            byte[] encryptedUsername = encryptString(name, secretKey, cipher);
            writeToPasswordList(encryptedUsername.toString(), encryptedPassword.toString());

            //String decryptedData = decryptString(encryptedData, secretKey, cipher);
            //System.out.println("Decrypted: " + decryptedData);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    static void writeToPasswordList(String userKey, String userPassword)
    {
        try {
            FileWriter fw = new FileWriter("PasswordList.txt", true);
            fw.write(userKey + ": " + userPassword + System.getProperty("line.separator"));
            fw.close();
            System.out.println("Content successfully written to PasswordList.txt");
        } catch(IOException io) {
            System.out.println("An error occured!");
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
