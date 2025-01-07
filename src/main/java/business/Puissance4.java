package business;

/**
 * Classe représentant la gestion métier du jeu Puissance 4.
 * Cette classe gère la progression du jeu, détermine le vainqueur et
 * gère l'abandon d'un joueur.
 */
public class Puissance4 {

    private Partie partie;

    /**
     * Constructeur par défaut qui crée une nouvelle partie.
     */
    public Puissance4() {
        this.partie = new Partie();
    }

    /**
     * Constructeur permettant de charger une partie sauvegardée.
     *
     * @param partie La partie sauvegardée.
     * @throws IllegalArgumentException si la partie fournie est null.
     */
    public Puissance4(Partie partie) {
        if (partie == null) {
            throw new IllegalArgumentException("La partie ne peut pas être null.");
        }
        this.partie = partie;
    }

    /**
     * Vérifie si la partie est terminée.
     *
     * @return true si la partie est finie, sinon false.
     */
    public boolean gameIsOver() {
        return partie.isPartieFinie();
    }

    /**
     * Permet de jouer un jeton dans une colonne donnée.
     *
     * @param numColonne Le numéro de la colonne (0-indexé).
     * @throws IllegalArgumentException si la colonne est invalide.
     * @throws Puissance4Exception si la colonne est pleine.
     * @throws IllegalStateException si la partie est déjà terminée.
     */
    public void jouer(int numColonne) throws Puissance4Exception {
        if (gameIsOver()) {
            throw new IllegalStateException("La partie est déjà terminée.");
        }

        try {
            Grille grille = partie.getGrille();
            Joueur joueurCourant = partie.getJoueurCourant();

            // Insérer le jeton
            int ligne = grille.insererJeton(new Jeton(joueurCourant.getCouleur()), numColonne);
            Position position = new Position(ligne, numColonne);

            // Vérifier les conditions de victoire
            if (grille.alignementRealise(position)) {
                partie.setPartieFinie(true);
                partie.setGagnant(joueurCourant);
            } else if (grille.isFullGrille()) {
                partie.setPartieFinie(true);
            } else {
                partie.changerJoueur();
            }
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    /**
     * Permet d'abandonner la partie.
     * Cette action déclare l'autre joueur comme gagnant et termine la partie.
     */
    public void abandonner() {
        if (gameIsOver()) {
            throw new IllegalStateException("La partie est déjà terminée.");
        }

        Joueur joueurCourant = partie.getJoueurCourant();
        Joueur autreJoueur = (joueurCourant == partie.getJoueurs()[0])
                ? partie.getJoueurs()[1]
                : partie.getJoueurs()[0];

        partie.setParAbandon(true);
        partie.setPartieFinie(true);
        partie.setGagnant(autreJoueur);
    }

    /**
     * Retourne l'état de la partie.
     *
     * @return L'objet Partie en cours.
     */
    public Partie getPartie() {
        return partie;
    }
}
