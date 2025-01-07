package test;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class Puissance4ExceptionTest {

    @Test
    void testConstructeurParDefaut() {
        Puissance4Exception exception = new Puissance4Exception();
        assertNull(exception.getMessage());
        assertEquals(-1, exception.getColonne(), "La colonne par défaut devrait être -1.");
    }

    @Test
    void testConstructeurAvecMessage() {
        String message = "Erreur spécifique.";
        Puissance4Exception exception = new Puissance4Exception(message);
        assertEquals(message, exception.getMessage(), "Le message de l'exception ne correspond pas.");
        assertEquals(-1, exception.getColonne(), "La colonne par défaut devrait être -1.");
    }

    @Test
    void testConstructeurAvecMessageEtColonne() {
        String message = "Colonne pleine.";
        int colonne = 2;
        Puissance4Exception exception = new Puissance4Exception(message, colonne);
        assertEquals(message, exception.getMessage(), "Le message de l'exception ne correspond pas.");
        assertEquals(colonne, exception.getColonne(), "La colonne associée ne correspond pas.");
    }

    @Test
    void testConstructeurAvecCause() {
        Throwable cause = new IllegalArgumentException("Cause initiale.");
        Puissance4Exception exception = new Puissance4Exception(cause);
        assertEquals(cause, exception.getCause(), "La cause associée ne correspond pas.");
        assertEquals(-1, exception.getColonne(), "La colonne par défaut devrait être -1.");
    }

    @Test
    void testConstructeurAvecMessageCauseEtColonne() {
        String message = "Erreur complexe.";
        Throwable cause = new IllegalStateException("Cause sous-jacente.");
        int colonne = 5;
        Puissance4Exception exception = new Puissance4Exception(message, cause, colonne);

        assertEquals(message, exception.getMessage(), "Le message de l'exception ne correspond pas.");
        assertEquals(cause, exception.getCause(), "La cause associée ne correspond pas.");
        assertEquals(colonne, exception.getColonne(), "La colonne associée ne correspond pas.");
    }
}
