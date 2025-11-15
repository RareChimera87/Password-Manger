package security;

import java.security.SecureRandom;
import java.util.Base64;

public class SaltGenerator {

    public static byte[] generateSalt(int length) { // <- Recomendado usar minimo 16 bits

        SecureRandom random = new SecureRandom();


        byte[] salt = new byte[length];


        random.nextBytes(salt);

        return salt;
    }


    // Lo codifica en base 64 para poder guardarlo como string
    // Array Bytes -> String
    public static String encodeSalt(byte[] salt) {
        return Base64.getEncoder().encodeToString(salt);
    }

    public static byte[] decodeString(String s) {
        return Base64.getDecoder().decode(s);
    }

}