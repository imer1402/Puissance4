package business;

/**
 * Représente un joueur participant à une partie de Puissance 4.
 * Cette classe est immuable et associe un joueur à une couleur.
 */
public final class Joueur {

    /**
     * Couleur associée au joueur.
     */
    private final Couleur couleur;

    /**
     * Crée une instance immuable de Joueur avec une couleur donnée.
     *
     * @param couleur La couleur associée au joueur.
     * @throws IllegalArgumentException si la couleur est null.
     */
    public Joueur(Couleur couleur) {
        if (couleur == null) {
            throw new IllegalArgumentException("La couleur du joueur ne peut pas être null.");
        }
        this.couleur = couleur;
    }

    /**
     * Retourne la couleur associée au joueur.
     *
     * @return La couleur du joueur.
     */
    public Couleur getCouleur() {
        return couleur;
    }

    /**
     * Retourne une représentation textuelle du joueur.
     *
     * @return Une chaîne de caractères décrivant le joueur.
     */
    @Override
    public String toString() {
        return "Joueur avec la couleur: " + couleur;
    }
}
