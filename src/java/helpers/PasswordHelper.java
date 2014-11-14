package helpers;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordHelper {

    private static final int ITERATIONS = 1609;
    private static final int KEY_LENGTH = 160; //bits
    private static final String ALGORITHM = "PBKDF2WithHmacSHA1";

    public static String hashPassword(String password, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
        char[] passwordChars = password.toCharArray();
        byte[] s = salt == null ? generateSalt() : Base64.getDecoder().decode(salt);
        PBEKeySpec spec = new PBEKeySpec(passwordChars, s, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory key = SecretKeyFactory.getInstance(ALGORITHM);
        byte[] hpd = key.generateSecret(spec).getEncoded();

        return Base64.getEncoder().encodeToString(hpd)+"_"+Base64.getEncoder().encodeToString(s);
    }

    private static byte[] generateSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[8];
        sr.nextBytes(salt);
        return salt;
    }
}
