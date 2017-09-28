package demo.atm.utils;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class PasswordGenerator {
    private final static int ITERATIONS = 10;
    private final static int KEY_LENGTH = 100;
    private final static String ALGORITHM = "PBKDF2WithHmacSHA512";


    public static String hashPassword(final String password, String salt) {
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITHM);
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), ITERATIONS, KEY_LENGTH);
            SecretKey key = skf.generateSecret(spec);
            byte[] res = key.getEncoded();
            return new String(res, StandardCharsets.UTF_8);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);// todo: handle it correctly
        }
    }

}
