package business;

/**
 * Classe représentant une grille de jeu pour le Puissance 4.
 */
public class Grille {

    /**
     * Nombre de lignes dans la grille.
     */
    public static final int NB_LIGNES = Config.NB_LIGNES;

    /**
     * Nombre de colonnes dans la grille.
     */
    public static final int NB_COLONNES = Config.NB_COLONNES;

    /**
     * Tableau de Jetons représentant la grille.
     */
    private final Jeton[][] plateauJetons;

    /**
     * Constructeur par défaut : crée une grille vide.
     */
    public Grille() {
        this.plateauJetons = new Jeton[NB_LIGNES][NB_COLONNES];
    }

    /**
     * Constructeur avec un plateau initial.
     * @param plateauJetons Le tableau de Jetons pour initialiser la grille.
     * @throws Puissance4Exception Si le tableau est invalide.
     */
    public Grille(Jeton[][] plateauJetons) throws Puissance4Exception {
        if (plateauJetons == null || plateauJetons.length != NB_LIGNES || plateauJetons[0].length != NB_COLONNES) {
            throw new Puissance4Exception("Le plateau fourni est invalide.");
        }
        this.plateauJetons = plateauJetons;
    }

    /**
     * Retourne le Jeton à une position donnée ou null si la case est vide.
     * @param position La position à vérifier.
     * @return Le Jeton à la position donnée ou null.
     */
    public Jeton getJeton(Position position) {
        if (position == null || position.getLigne() < 0 || position.getLigne() >= NB_LIGNES
                || position.getColonne() < 0 || position.getColonne() >= NB_COLONNES) {
            throw new IllegalArgumentException("Position invalide : " + position);
        }
        return plateauJetons[position.getLigne()][position.getColonne()];
    }

    /**
     * Vérifie si une colonne est pleine.
     * @param numColonne Le numéro de la colonne à vérifier.
     * @return true si la colonne est pleine, sinon false.
     */
    public boolean isFullColonne(int numColonne) {
        if (numColonne < 0 || numColonne >= NB_COLONNES) {
            throw new IllegalArgumentException("Numéro de colonne invalide : " + numColonne);
        }
        return plateauJetons[0][numColonne] != null;
    }

    /**
     * Insère un Jeton dans une colonne donnée.
     * @param jeton Le Jeton à insérer.
     * @param numColonne Le numéro de la colonne.
     * @return La ligne où le Jeton a été inséré.
     * @throws Puissance4Exception Si la colonne est pleine.
     */
    public int insererJeton(Jeton jeton, int numColonne) throws Puissance4Exception {
        if (numColonne < 0 || numColonne >= NB_COLONNES) {
            throw new IllegalArgumentException("Numéro de colonne invalide : " + numColonne);
        }
        for (int i = NB_LIGNES - 1; i >= 0; i--) {
            if (plateauJetons[i][numColonne] == null) {
                plateauJetons[i][numColonne] = jeton;
                return i;
            }
        }
        throw new Puissance4Exception("La colonne " + numColonne + " est pleine.", numColonne);
    }

    /**
     * Vérifie si la grille est entièrement remplie.
     * @return true si la grille est pleine, sinon false.
     */
    public boolean isFullGrille() {
        for (int j = 0; j < NB_COLONNES; j++) {
            if (!isFullColonne(j)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Vérifie s'il existe un alignement de 4 jetons passant par une position donnée.
     * @param position La position de départ.
     * @return true si un alignement est trouvé, sinon false.
     */
    public boolean alignementRealise(Position position) {
        if (position == null || position.getLigne() < 0 || position.getLigne() >= NB_LIGNES
                || position.getColonne() < 0 || position.getColonne() >= NB_COLONNES) {
            throw new IllegalArgumentException("Position invalide : " + position);
        }

        Jeton jeton = getJeton(position);
        if (jeton == null) {
            return false;
        }

        return alignementHorizontal(position, jeton) >= 4 ||
                alignementVertical(position, jeton) >= 4 ||
                alignementDiagonale(position, jeton) >= 4;
    }

    /**
     * Retourne le nombre de jetons alignés horizontalement.
     * @param position La position de départ.
     * @param jeton Le Jeton à vérifier.
     * @return Le nombre de jetons alignés horizontalement.
     */
    private int alignementHorizontal(Position position, Jeton jeton) {
        int ligne = position.getLigne();
        int colonne = position.getColonne();
        int nbAlignes = 1;

        for (int j = colonne - 1; j >= 0 && plateauJetons[ligne][j] == jeton; j--) {
            nbAlignes++;
        }

        for (int j = colonne + 1; j < NB_COLONNES && plateauJetons[ligne][j] == jeton; j++) {
            nbAlignes++;
        }

        return nbAlignes;
    }

    /**
     * Retourne le nombre de jetons alignés verticalement.
     * @param position La position de départ.
     * @param jeton Le Jeton à vérifier.
     * @return Le nombre de jetons alignés verticalement.
     */
    private int alignementVertical(Position position, Jeton jeton) {
        int ligne = position.getLigne();
        int colonne = position.getColonne();
        int nbAlignes = 1;

        for (int i = ligne + 1; i < NB_LIGNES && plateauJetons[i][colonne] == jeton; i++) {
            nbAlignes++;
        }

        return nbAlignes;
    }

    /**
     * Retourne le nombre de jetons alignés diagonalement.
     * @param position La position de départ.
     * @param jeton Le Jeton à vérifier.
     * @return Le nombre maximum de jetons alignés diagonalement.
     */
    private int alignementDiagonale(Position position, Jeton jeton) {
        return Math.max(alignementDiagonal(position, jeton, -1, -1) + alignementDiagonal(position, jeton, 1, 1) - 1,
                alignementDiagonal(position, jeton, -1, 1) + alignementDiagonal(position, jeton, 1, -1) - 1);
    }

    /**
     * Retourne le nombre de jetons alignés dans une direction diagonale spécifique.
     * @param position La position de départ.
     * @param jeton Le Jeton à vérifier.
     * @param deltaX Direction verticale.
     * @param deltaY Direction horizontale.
     * @return Le nombre de jetons alignés dans cette diagonale.
     */
    private int alignementDiagonal(Position position, Jeton jeton, int deltaX, int deltaY) {
        int nbAlignes = 1;
        int ligne = position.getLigne();
        int colonne = position.getColonne();

        for (int i = 1; i < 4; i++) {
            int newLigne = ligne + i * deltaX;
            int newColonne = colonne + i * deltaY;
            if (newLigne >= 0 && newLigne < NB_LIGNES && newColonne >= 0 && newColonne < NB_COLONNES
                    && plateauJetons[newLigne][newColonne] == jeton) {
                nbAlignes++;
            } else {
                break;
            }
        }

        for (int i = 1; i < 4; i++) {
            int newLigne = ligne - i * deltaX;
            int newColonne = colonne - i * deltaY;
            if (newLigne >= 0 && newLigne < NB_LIGNES && newColonne >= 0 && newColonne < NB_COLONNES
                    && plateauJetons[newLigne][newColonne] == jeton) {
                nbAlignes++;
            } else {
                break;
            }
        }

        return nbAlignes;
    }
}
