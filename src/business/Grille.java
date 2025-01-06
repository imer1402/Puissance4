package business;

public class Grille {
    private static final int NB_LIGNES = Config.NB_LIGNES;
    private static final int NB_COLONNES = Config.NB_COLONNES;
    private Jeton[][] plateauJetons;

    public Grille() {
        plateauJetons = new Jeton[NB_LIGNES][NB_COLONNES];
    }

    public Jeton[][] getJetons() {
        return plateauJetons;
    }

    public int insererJeton(Jeton jeton, int numColonne) {
        for (int i = NB_LIGNES - 1; i >= 0; i--) {
            if (plateauJetons[i][numColonne] == null) {
                plateauJetons[i][numColonne] = jeton;
                return i;
            }
        }
        return -1; // Colonne pleine
    }

    public boolean isFullColonne(int numColonne) {
        return plateauJetons[0][numColonne] != null;
    }

    public boolean isFullGrille() {
        for (int i = 0; i < NB_COLONNES; i++) {
            if (!isFullColonne(i)) return false;
        }
        return true;
    }

    public boolean alignementRealise(Position position) {
        // Logique pour vérifier l'alignement (horizontal, vertical, diagonal)
        return false; // Exemple fictif
    }
}
