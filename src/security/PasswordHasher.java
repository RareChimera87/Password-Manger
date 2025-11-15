package security;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordHasher {

    // Configuraciones recomendadas para PBKDF2
    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final int KEY_LENGTH = 256; // 256 bits
    private static final int ITERATIONS = 310000; // Mínimo recomendado

    /**
     * Genera el hash de una contraseña usando un salt y PBKDF2.
     * @param password La contraseña de texto plano.
     * @param saltBytes El salt como un array de bytes.
     * @return El hash resultante como un array de bytes.
     */
    public static byte[] hashPassword(String password, byte[] saltBytes)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        // 1. Especifica la contraseña y el salt
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, ITERATIONS, KEY_LENGTH);

        // 2. Obtiene la fábrica de claves para el algoritmo PBKDF2
        SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITHM);

        // 3. Genera la clave hasheada
        return skf.generateSecret(spec).getEncoded();
    }

    public static void main(String[] args) {
        String password = "MiPasswordSecreto123!";
        int saltLength = 16;

        // --- 1. Generación y Hasheo ---

        // Genera el salt (byte[])
        byte[] saltBytes = SaltGenerator.generateSalt(saltLength);

        // Hashea la contraseña usando el array de bytes del salt
        try {
            byte[] hashedPasswordBytes = hashPassword(password, saltBytes);

            // --- 2. Almacenamiento ---

            // Codifica el hash y el salt para almacenar en la DB como String
            String storedSalt = SaltGenerator.encodeSalt(saltBytes);
            String storedHash = SaltGenerator.encodeSalt(hashedPasswordBytes);

            System.out.println("Password: " + password);
            System.out.println("--- Almacenar en DB ---");
            System.out.println("Salt codificado (Base64): " + storedSalt);
            System.out.println("Hash codificado (Base64): " + storedHash);

            // --- 3. Verificación (Ejemplo de login) ---

            String loginAttempt = "MiPasswordSecreto123!";

            // 1. Recupera el salt de la DB y lo decodifica a byte[]
            byte[] retrievedSalt = SaltGenerator.decodeString(storedSalt);

            // 2. Hashea la contraseña de intento usando el salt recuperado
            byte[] attemptedHashBytes = hashPassword(loginAttempt, retrievedSalt);
            String attemptedHash = SaltGenerator.encodeSalt(attemptedHashBytes);

            // 3. Compara el nuevo hash con el hash almacenado
            boolean passwordMatch = attemptedHash.equals(storedHash);

            System.out.println("-------------------------");
            System.out.println("Verificación exitosa: " + passwordMatch);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }
}