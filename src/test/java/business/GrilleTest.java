package business;

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
    void testConstructeurParDefaut() {
        for (int i = 0; i < Grille.NB_LIGNES; i++) {
            for (int j = 0; j < Grille.NB_COLONNES; j++) {
                assertNull(grille.getJeton(new Position(i, j)), "La grille doit être vide au départ.");
            }
        }
    }

    @Test
    void testConstructeurAvecPlateauValide() throws Puissance4Exception {
        Jeton[][] plateau = new Jeton[Grille.NB_LIGNES][Grille.NB_COLONNES];
        Grille grilleTest = new Grille(plateau);
        assertNotNull(grilleTest, "La grille doit être créée avec un plateau valide.");
    }

    @Test
    void testConstructeurAvecPlateauInvalide() {
        assertThrows(Puissance4Exception.class, () -> new Grille(null), "Le constructeur doit lever une exception si le plateau est null.");

        Jeton[][] plateauInvalide = new Jeton[Grille.NB_LIGNES - 1][Grille.NB_COLONNES];
        assertThrows(Puissance4Exception.class, () -> new Grille(plateauInvalide), "Le constructeur doit lever une exception si le plateau a des dimensions incorrectes.");
    }

    @Test
    void testInsertionJetonColonneValide() throws Puissance4Exception {
        Jeton jeton = new Jeton(Couleur.ROUGE);
        int ligneInseree = grille.insererJeton(jeton, 0);
        assertEquals(Grille.NB_LIGNES - 1, ligneInseree, "Le jeton doit être inséré à la dernière ligne.");
        assertEquals(jeton, grille.getJeton(new Position(ligneInseree, 0)), "Le jeton doit être inséré à la bonne position.");
    }

    @Test
    void testInsertionJetonColonneInvalide() {
        Jeton jeton = new Jeton(Couleur.JAUNE);

        assertThrows(IllegalArgumentException.class, () -> grille.insererJeton(jeton, -1), "Une exception doit être levée pour une colonne invalide (négative).");
        assertThrows(IllegalArgumentException.class, () -> grille.insererJeton(jeton, Grille.NB_COLONNES), "Une exception doit être levée pour une colonne hors limites.");
    }

    @Test
    void testInsertionJetonColonnePleine() throws Puissance4Exception {
        Jeton jeton = new Jeton(Couleur.JAUNE);

        for (int i = 0; i < Grille.NB_LIGNES; i++) {
            grille.insererJeton(jeton, 0);
        }

        assertThrows(Puissance4Exception.class, () -> grille.insererJeton(jeton, 0), "Une exception doit être levée si la colonne est pleine.");
    }

    @Test
    void testGrillePleine() throws Puissance4Exception {
        Jeton jeton = new Jeton(Couleur.ROUGE);

        for (int col = 0; col < Grille.NB_COLONNES; col++) {
            for (int ligne = 0; ligne < Grille.NB_LIGNES; ligne++) {
                grille.insererJeton(jeton, col);
            }
        }

        assertTrue(grille.isFullGrille(), "La grille doit être pleine.");
    }

    @Test
    void testAlignementHorizontal() throws Puissance4Exception {
        Jeton jeton = new Jeton(Couleur.ROUGE);

        grille.insererJeton(jeton, 0);
        grille.insererJeton(jeton, 1);
        grille.insererJeton(jeton, 2);
        grille.insererJeton(jeton, 3);

        Position position = new Position(Grille.NB_LIGNES - 1, 1);
        assertTrue(grille.alignementRealise(position), "Un alignement horizontal doit être détecté.");
    }

    @Test
    void testAlignementVertical() throws Puissance4Exception {
        Jeton jeton = new Jeton(Couleur.JAUNE);

        grille.insererJeton(jeton, 0);
        grille.insererJeton(jeton, 0);
        grille.insererJeton(jeton, 0);
        grille.insererJeton(jeton, 0);

        Position position = new Position(Grille.NB_LIGNES - 4, 0);
        assertTrue(grille.alignementRealise(position), "Un alignement vertical doit être détecté.");
    }

    @Test
    void testAlignementDiagonal() throws Puissance4Exception {
        Jeton jetonRouge = new Jeton(Couleur.ROUGE);
        Jeton jetonJaune = new Jeton(Couleur.JAUNE);

        grille.insererJeton(jetonRouge, 0); // Ligne 5, Colonne 0
        grille.insererJeton(jetonJaune, 1);
        grille.insererJeton(jetonRouge, 1); // Ligne 4, Colonne 1
        grille.insererJeton(jetonJaune, 2);
        grille.insererJeton(jetonJaune, 2);
        grille.insererJeton(jetonRouge, 2); // Ligne 3, Colonne 2
        grille.insererJeton(jetonJaune, 3);
        grille.insererJeton(jetonJaune, 3);
        grille.insererJeton(jetonJaune, 3);
        grille.insererJeton(jetonRouge, 3); // Ligne 2, Colonne 3

        Position position = new Position(2, 3);
        assertTrue(grille.alignementRealise(position), "Un alignement diagonal doit être détecté.");
    }
}
