

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.challenge.oddeven.OddOrEven;
import org.junit.jupiter.api.Test;

public class OddOrEvenTest {

    @Test
    public void isEven_true() {
        assertTrue(OddOrEven.isEven(0));
        assertTrue(OddOrEven.isEven(2));
        assertTrue(OddOrEven.isEven(4));
        assertTrue(OddOrEven.isEven(6));
        assertTrue(OddOrEven.isEven(8));
        assertTrue(OddOrEven.isEven(-20));
        assertTrue(OddOrEven.isEven(100));
        assertTrue(OddOrEven.isEven(-46));
    }

    @Test
    public void isEven_false() {
        assertFalse(OddOrEven.isEven(1));
        assertFalse(OddOrEven.isEven(3));
        assertFalse(OddOrEven.isEven(5));
        assertFalse(OddOrEven.isEven(7));
        assertFalse(OddOrEven.isEven(9));
        assertFalse(OddOrEven.isEven(-11));
        assertFalse(OddOrEven.isEven(93));
        assertFalse(OddOrEven.isEven(-75));
    }
}
