package business;

/**
 * La classe Jeton représente un jeton dans le jeu.
 * Chaque jeton est associé à une couleur définie par l'énumération Couleur.
 */
public class Jeton {

    // Attribut représentant la couleur du jeton et immuable
    private final Couleur couleur;

    /**
     * Constructeur de la classe Jeton.
     * @param couleur La couleur du jeton (JAUNE ou ROUGE)
     */
    public Jeton(Couleur couleur) {
        this.couleur = couleur;
    }

    /**
     * Accesseur pour la couleur du jeton.
     * @return La couleur du jeton
     */
    public Couleur getCouleur() {
        return couleur;
    }

    /**
     * Méthode pour représenter le jeton sous forme de chaîne.
     * @return Une représentation textuelle de la couleur du jeton
     */
    @Override
    public String toString() {
        return "Jeton de couleur: " + couleur;
    }
}


