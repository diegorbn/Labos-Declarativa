package backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConnectionTest {
    private Connection connection;

    @BeforeEach
    void setUp() {
        connection = new Connection();
    }

    @Test
    void addsTwoNumbersInProlog() {
        assertEquals(9.0, connection.add(4.0, 5.0));
    }

    @Test
    void subtractsTwoNumbersInProlog() {
        assertEquals(-1.0, connection.subtract(2.0, 3.0));
    }

    @Test
    void supportsDecimalAndNegativeNumbers() {
        assertEquals(-1.25, connection.add(-2.5, 1.25));
        assertEquals(3.75, connection.subtract(2.5, -1.25));
    }

    @Test
    void rejectsNonFiniteNumbers() {
        assertThrows(IllegalArgumentException.class,
                () -> connection.add(Double.NaN, 1.0));
    }
}
