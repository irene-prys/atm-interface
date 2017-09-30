package demo.atm;

import demo.atm.utils.PasswordGenerator;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class PasswordGeneratorTest {

    @Test
    public void shouldDefineTwoSamePasswordsEqual() {
        String planePassword = "HelloWorld";
        String salt = UUID.randomUUID().toString();
        String hashedPassword1 = PasswordGenerator.hashPassword(planePassword, salt);
        String hashedPassword2 = PasswordGenerator.hashPassword(planePassword, salt);

        assertEquals(hashedPassword1, hashedPassword2);
        assertNotNull(hashedPassword1);
        assertNotEquals("", hashedPassword1);
    }
}
