package business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour la classe Puissance4.
 * Cette classe teste les principales fonctionnalités du jeu Puissance 4.
 */
class Puissance4Test {

    private Puissance4 puissance4;

    /**
     * Initialisation avant chaque test.
     */
    @BeforeEach
    void setUp() {
        puissance4 = new Puissance4();
    }

    /**
     * Teste le constructeur par défaut.
     * Vérifie que la partie est correctement initialisée.
     */
    @Test
    void testConstructeurParDefaut() {
        assertNotNull(puissance4.getPartie(), "La partie doit être initialisée dans le constructeur par défaut.");
        assertFalse(puissance4.gameIsOver(), "La partie ne doit pas être terminée au début.");
    }

    /**
     * Teste si l'état de fin de partie est correctement détecté.
     */
    @Test
    void testGameIsOver() {
        assertFalse(puissance4.gameIsOver(), "La partie ne doit pas être terminée au début.");

        puissance4.getPartie().setPartieFinie(true);
        assertTrue(puissance4.gameIsOver(), "La partie doit être considérée comme terminée.");
    }

    /**
     * Teste le jeu dans une colonne valide.
     * Vérifie que le jeton est correctement inséré.
     */
    @Test
    void testJouerColonneValide() throws Puissance4Exception {
        int colonne = 0;
        puissance4.jouer(colonne);

        assertNotNull(puissance4.getPartie().getGrille().getJeton(new Position(Grille.NB_LIGNES - 1, colonne)),
                "Un jeton doit être présent dans la colonne jouée.");
    }

    /**
     * Teste le comportement lorsqu'une colonne pleine est jouée.
     */
    @Test
    void testJouerColonnePleine() {
        int colonne = 0;
        try {
            for (int i = 0; i < Grille.NB_LIGNES; i++) {
                puissance4.jouer(colonne);
            }

            assertThrows(Puissance4Exception.class, () -> puissance4.jouer(colonne),
                    "Une exception doit être levée si la colonne est pleine.");
        } catch (Puissance4Exception e) {
            fail("Aucune exception ne devrait être levée avant que la colonne ne soit pleine.");
        }
    }

    /**
     * Teste le comportement lorsqu'un joueur abandonne.
     * Vérifie que l'autre joueur est déclaré gagnant et que la partie est terminée.
     */
    @Test
    void testAbandonner() {
        puissance4.abandonner();

        assertTrue(puissance4.getPartie().isParAbandon(), "La partie doit être marquée comme terminée par abandon.");
        assertTrue(puissance4.gameIsOver(), "La partie doit être considérée comme terminée après abandon.");
        assertNotNull(puissance4.getPartie().getGagnant(), "Un gagnant doit être déclaré après abandon.");
    }

    /**
     * Teste le comportement lorsqu'un joueur tente de jouer après la fin de la partie.
     */
    @Test
    void testJouerApresFinDePartie() {
        puissance4.getPartie().setPartieFinie(true);

        assertThrows(IllegalStateException.class, () -> puissance4.jouer(0),
                "Une exception doit être levée si un joueur tente de jouer après la fin de la partie.");
    }

    /**
     * Teste le constructeur avec une partie sauvegardée.
     * Vérifie que la partie sauvegardée est correctement chargée.
     */
    @Test
    void testConstructeurAvecPartieSauvegardee() {
        Partie partieSauvegardee = new Partie();
        Puissance4 jeuCharge = new Puissance4(partieSauvegardee);

        assertNotNull(jeuCharge.getPartie(), "La partie sauvegardée doit être chargée correctement.");
        assertEquals(partieSauvegardee, jeuCharge.getPartie(), "La partie sauvegardée doit correspondre à celle chargée.");
    }
}