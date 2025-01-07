package test;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GrilleTest {

    private Grille grille;

    @BeforeEach
    void setUp() {
        grille = new Grille();
    }

    @Test
    void testInsertionJeton() {
        Jeton jeton = new Jeton(Couleur.JAUNE);
        int ligneInseree = grille.insererJeton(jeton, 0);

        assertEquals(Grille.NB_LIGNES - 1, ligneInseree, "Le jeton doit être inséré à la dernière ligne.");
        assertEquals(jeton, grille.getJeton(new Position(ligneInseree, 0)), "Le jeton doit être présent à la bonne position.");
    }

    @Test
    void testInsertionColonnePleine() {
        Jeton jeton = new Jeton(Couleur.ROUGE);

        for (int i = 0; i < Grille.NB_LIGNES; i++) {
            grille.insererJeton(jeton, 0);
        }

        assertThrows(Puissance4Exception.class, () -> grille.insererJeton(jeton, 0), "Une exception doit être levée si la colonne est pleine.");
    }

    @Test
    void testColonneRemplie() {
        Jeton jeton = new Jeton(Couleur.ROUGE);

        for (int i = 0; i < Grille.NB_LIGNES; i++) {
            grille.insererJeton(jeton, 1);
        }

        assertTrue(grille.isFullColonne(1), "La colonne 1 doit être pleine.");
        assertFalse(grille.isFullColonne(0), "La colonne 0 ne doit pas être pleine.");
    }

    @Test
    void testGrilleRemplie() {
        Jeton jeton = new Jeton(Couleur.JAUNE);

        for (int col = 0; col < Grille.NB_COLONNES; col++) {
            for (int ligne = 0; ligne < Grille.NB_LIGNES; ligne++) {
                grille.insererJeton(jeton, col);
            }
        }

        assertTrue(grille.isFullGrille(), "La grille doit être pleine.");
    }

    @Test
    void testAlignementHorizontal() {
        Jeton jeton = new Jeton(Couleur.ROUGE);

        grille.insererJeton(jeton, 1);
        grille.insererJeton(jeton, 2);
        grille.insererJeton(jeton, 3);
        grille.insererJeton(jeton, 4);

        Position position = new Position(Grille.NB_LIGNES - 1, 2);

        assertTrue(grille.alignementRealise(position), "Un alignement horizontal doit être détecté.");
    }

    @Test
    void testAlignementVertical() {
        Jeton jeton = new Jeton(Couleur.JAUNE);

        grille.insererJeton(jeton, 0);
        grille.insererJeton(jeton, 0);
        grille.insererJeton(jeton, 0);
        grille.insererJeton(jeton, 0);

        Position position = new Position(Grille.NB_LIGNES - 4, 0);

        assertTrue(grille.alignementRealise(position), "Un alignement vertical doit être détecté.");
    }

    @Test
    void testAlignementDiagonal() {
        Jeton jetonJaune = new Jeton(Couleur.JAUNE);
        Jeton jetonRouge = new Jeton(Couleur.ROUGE);

        grille.insererJeton(jetonJaune, 0);
        grille.insererJeton(jetonRouge, 1);
        grille.insererJeton(jetonJaune, 1);
        grille.insererJeton(jetonRouge, 2);
        grille.insererJeton(jetonRouge, 2);
        grille.insererJeton(jetonJaune, 2);
        grille.insererJeton(jetonRouge, 3);
        grille.insererJeton(jetonRouge, 3);
        grille.insererJeton(jetonRouge, 3);
        grille.insererJeton(jetonJaune, 3);

        Position position = new Position(Grille.NB_LIGNES - 4, 0);

        assertTrue(grille.alignementRealise(position), "Un alignement diagonal doit être détecté.");
    }

    @Test
    void testPositionInvalide() {
        Position positionInvalide = new Position(-1, 0);
        assertThrows(IllegalArgumentException.class, () -> grille.getJeton(positionInvalide), "Une position invalide doit lever une exception.");
    }
}
