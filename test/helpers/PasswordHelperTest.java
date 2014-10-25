package helpers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import helpers.PasswordHelper;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Cu Beo
 */
public class PasswordHelperTest {

    String password = "mashiro";
    String hashedPassword = "";

    public PasswordHelperTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        try {
            hashedPassword = PasswordHelper.hashPassword(password, null);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PasswordHelperTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(PasswordHelperTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(PasswordHelperTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void test_password_and_salt_equal_hashed_password() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
        System.out.println("test_password_and_salt_equal_hashed_password");
        String salt = hashedPassword.split("_")[1];
        assertEquals(hashedPassword, PasswordHelper.hashPassword(password, salt));
    }
}
