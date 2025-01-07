package business;

/**
 * La classe Grille représente une grille de jeu composée de Jetons.
 * Elle permet de gérer les opérations liées à une grille de Puissance 4.
 */
public class Grille {

    /**
     * Nombre de lignes dans la grille, défini par la classe Config.
     */
    public static final int NB_LIGNES = Config.NB_LIGNES;

    /**
     * Nombre de colonnes dans la grille, défini par la classe Config.
     */
    public static final int NB_COLONNES = Config.NB_COLONNES;

    /**
     * Tableau de Jetons représentant la grille.
     */
    private final Jeton[][] plateauJetons;

    /**
     * Constructeur par défaut.
     * Crée une grille vide avec les dimensions NB_LIGNES x NB_COLONNES.
     */
    public Grille() {
        this.plateauJetons = new Jeton[NB_LIGNES][NB_COLONNES];
    }

    /**
     * Constructeur avec un plateau initial.
     * @param plateauJetons Le tableau 2D de Jetons pour initialiser la grille.
     * @throws Puissance4Exception Si le tableau est null ou invalide.
     */
    public Grille(Jeton[][] plateauJetons) {
        if (plateauJetons == null || plateauJetons.length != NB_LIGNES || plateauJetons[0].length != NB_COLONNES) {
            throw new Puissance4Exception("Le plateau fourni est invalide.");
        }
        this.plateauJetons = plateauJetons;
    }

    /**
     * Retourne le Jeton à une position donnée.
     * @param position La position dans la grille.
     * @return Le Jeton à la position donnée, ou null si la case est vide.
     * @throws IllegalArgumentException Si la position est invalide.
     */
    public Jeton getJeton(Position position) {
        if (position == null || position.getLigne() < 0 || position.getLigne() >= NB_LIGNES
                || position.getColonne() < 0 || position.getColonne() >= NB_COLONNES) {
            throw new IllegalArgumentException("Position invalide : " + position);
        }
        return plateauJetons[position.getLigne()][position.getColonne()];
    }

    /**
     * Vérifie si une colonne est remplie.
     * @param numColonne Le numéro de la colonne (0-indexé).
     * @return true si la colonne est remplie, sinon false.
     * @throws IllegalArgumentException Si le numéro de colonne est invalide.
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
     * @param numColonne Le numéro de la colonne (0-indexé).
     * @return L'indice de la ligne où le Jeton a été inséré.
     * @throws IllegalArgumentException Si le numéro de colonne est invalide.
     * @throws Puissance4Exception Si la colonne est déjà remplie.
     */
    public int insererJeton(Jeton jeton, int numColonne) {
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
     * Vérifie si la grille est complètement remplie.
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
     * @throws IllegalArgumentException Si la position est invalide.
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
     * Retourne le nombre de jetons alignés horizontalement avec la même couleur.
     * @param position La position de départ.
     * @param jeton Le jeton à comparer.
     * @return Le nombre de jetons alignés horizontalement.
     */
    private int alignementHorizontal(Position position, Jeton jeton) {
        int ligne = position.getLigne();
        int colonne = position.getColonne();
        int nbAlignes = 1;

        // À gauche
        for (int j = colonne - 1; j >= 0 && plateauJetons[ligne][j] == jeton; j--) {
            nbAlignes++;
        }

        // À droite
        for (int j = colonne + 1; j < NB_COLONNES && plateauJetons[ligne][j] == jeton; j++) {
            nbAlignes++;
        }

        return nbAlignes;
    }

    /**
     * Retourne le nombre de jetons alignés verticalement avec la même couleur.
     * @param position La position de départ.
     * @param jeton Le jeton à comparer.
     * @return Le nombre de jetons alignés verticalement.
     */
    private int alignementVertical(Position position, Jeton jeton) {
        int ligne = position.getLigne();
        int colonne = position.getColonne();
        int nbAlignes = 1;

        // Vers le bas
        for (int i = ligne + 1; i < NB_LIGNES && plateauJetons[i][colonne] == jeton; i++) {
            nbAlignes++;
        }

        return nbAlignes;
    }

    /**
     * Retourne le nombre de jetons alignés diagonalement avec la même couleur.
     * @param position La position de départ.
     * @param jeton Le jeton à comparer.
     * @return Le nombre de jetons alignés diagonalement.
     */
    private int alignementDiagonale(Position position, Jeton jeton) {
        return alignementDiagonal(position, jeton, -1, -1) + alignementDiagonal(position, jeton, 1, 1) - 1 ||
                alignementDiagonal(position, jeton, -1, 1) + alignementDiagonal(position, jeton, 1, -1) - 1;
    }

    private int alignementDiagonal(Position position, Jeton jeton, int deltaX, int deltaY) {
        int nbAlignes = 0;
        int ligne = position.getLigne();
        int colonne = position.getColonne();

        for (int i = 0; i < NB_LIGNES && grille