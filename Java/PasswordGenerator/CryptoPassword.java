import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class CryptoPassword {
    public SecretKey generateKey(String encryptionType) throws Exception
    {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(encryptionType);
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey;
    }

    public byte[] encryptString(String dataToEncrypt, SecretKey secretKey, Cipher cipher) throws Exception
    {
        byte[] text = dataToEncrypt.getBytes("UTF-8");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(text);
    }

    public String decryptString(byte[] dataToDecrypt, SecretKey secretKey, Cipher cipher) throws Exception
    {
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedText = cipher.doFinal(dataToDecrypt);
        return new String(decryptedText);
    }
}
