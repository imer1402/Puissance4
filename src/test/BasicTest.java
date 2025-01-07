package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BasicTest {
    @Test
    void testIntegration() {
        assertEquals(4, 2 + 2, "L'addition de 2 et 2 devrait être égale à 4.");
    }
}

