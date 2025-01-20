package com.example.passwordmanager.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class EncryptionUtil {
    private static final String ALGORITHM = "AES"; // AES algorithm

    // Encryption method
    public static String encrypt(String plaintext, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");  // Specify AES/ECB with PKCS5 padding
        cipher.init(Cipher.ENCRYPT_MODE, key);  // Initialize cipher for encryption
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes("UTF-8"));  // Encrypt the plaintext
        return Base64.getEncoder().encodeToString(encryptedBytes);  // Return Base64 encoded encrypted string
    }

    // Decryption method
    public static String decrypt(String encryptedText, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");  // Specify AES/ECB with PKCS5 padding
        cipher.init(Cipher.DECRYPT_MODE, key);  // Initialize cipher for decryption
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);  // Decode the Base64 encrypted text
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);  // Decrypt the bytes
        return new String(decryptedBytes, "UTF-8");  // Return the decrypted string
    }

    // Key generation method
    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);  // Use AES for key generation
        keyGen.init(256);  // Initialize with 256-bit key size (can also be 128 or 192)
        return keyGen.generateKey();  // Generate and return the AES key
    }
    // Convert a string to a SecretKey
    public static SecretKey getKeyFromString(String keyString) {
        byte[] decodedKey = Base64.getDecoder().decode(keyString);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, ALGORITHM);
    }

    // Convert a SecretKey to a string
    public static String keyToString(SecretKey key) {
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }
}
