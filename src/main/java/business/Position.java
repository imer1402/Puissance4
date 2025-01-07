package business;

/**
 * La classe Position permet de désigner des positions sur la grille de jeu.
 * Une position est représentée par une ligne et une colonne. Les indices doivent être supérieurs ou égaux à zéro.
 */
public class Position {

    /**
     * Ligne de la position.
     */
    private final int ligne;

    /**
     * Colonne de la position.
     */
    private final int colonne;

    /**
     * Constructeur de la classe Position.
     * @param ligne La ligne de la position (doit être >= 0).
     * @param colonne La colonne de la position (doit être >= 0).
     * @throws IllegalArgumentException Si les indices sont négatifs.
     */
    public Position(int ligne, int colonne) {
        if (ligne < 0 || colonne < 0) {
            throw new IllegalArgumentException("Les indices de ligne et de colonne doivent être >= 0.");
        }
        this.ligne = ligne;
        this.colonne = colonne;
    }

    /**
     * Accesseur pour la ligne.
     * @return La ligne de la position.
     */
    public int getLigne() {
        return ligne;
    }

    /**
     * Accesseur pour la colonne.
     * @return La colonne de la position.
     */
    public int getColonne() {
        return colonne;
    }

    /**
     * Méthode pour comparer deux positions.
     * @param obj L'objet à comparer.
     * @return true si les positions sont égales, sinon false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Position position = (Position) obj;
        return ligne == position.ligne && colonne == position.colonne;
    }

    /**
     * Méthode pour calculer le hash code de la position.
     * @return Le hash code basé sur les coordonnées.
     */
    @Override
    public int hashCode() {
        return 31 * ligne + colonne;
    }

    /**
     * Méthode pour représenter la position sous forme de chaîne.
     * @return Une chaîne représentant la position.
     */
    @Override
    public String toString() {
        return "Position{" +
                "ligne=" + ligne +
                ", colonne=" + colonne +
                '}';
    }
}