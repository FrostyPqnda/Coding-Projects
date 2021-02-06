import java.util.Scanner;
import java.util.InputMismatchException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class UserData extends PasswordGenerator {

    //private static final String UNICODE_FORMAT = "UTF-8";
    
    public static void main(String[] args) throws InputMismatchException
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a length: ");
        int input = scan.nextInt();
        String password = generatePassword(input);
        System.out.println("Password: " + password);
        scan.close();

        /*try {
            SecretKey secretKey = generateKey("AES");
            Cipher cipher = Cipher.getInstance("AES");

            byte[] encryptedData = encryptString(password, secretKey, cipher);
            System.out.println("Encrypted: " + encryptedData.toString());

            String decryptedData = decryptString(encryptedData, secretKey, cipher);
            System.out.println("Decrypted: " + decryptedData);
        } catch(Exception e) {
            System.out.println(e);
        } */
    }

    /*static SecretKey generateKey(String encryptionType)
    {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(encryptionType);
            SecretKey secretKey = keyGenerator.generateKey();
            return secretKey;
        } catch(Exception e) {
            return null;
        }
    }

    static byte[] encryptString(String dataToEncrypt, SecretKey secretKey, Cipher cipher)
    {
        try {
            byte[] text = dataToEncrypt.getBytes("UTF-8");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return cipher.doFinal(text);
        } catch(Exception e) {
            return null;
        }
    }

    static String decryptString(byte[] dataToDecrypt, SecretKey secretKey, Cipher cipher)
    {
        try {
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedText = cipher.doFinal(dataToDecrypt);
            return new String(decryptedText);
        } catch(Exception e) {
            return null;
        }
    }*/
}
