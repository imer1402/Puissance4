package business;

/**
 * Représente une partie de Puissance 4, fournissant les informations nécessaires
 * pour gérer le jeu entre deux joueurs.
 */
public class Partie {

    private final Grille grille;
    private final Joueur[] joueurs;
    private Joueur joueurCourant;
    private boolean partieFinie;
    private boolean parAbandon;
    private Joueur gagnant;

    /**
     * Initialise une nouvelle partie entre deux joueurs.
     * Le joueur qui commence est choisi aléatoirement.
     */
    public Partie() {
        this.grille = new Grille();

        // Création des joueurs
        Joueur joueurJaune = new Joueur(Couleur.JAUNE);
        Joueur joueurRouge = new Joueur(Couleur.ROUGE);
        this.joueurs = new Joueur[]{joueurJaune, joueurRouge};

        // Choix aléatoire du joueur qui commence
        this.joueurCourant = Math.random() < 0.5 ? joueurJaune : joueurRouge;

        this.partieFinie = false;
        this.parAbandon = false;
        this.gagnant = null;
    }

    /**
     * Retourne la grille de jeu.
     *
     * @return La grille.
     */
    public Grille getGrille() {
        return grille;
    }

    /**
     * Retourne le tableau des joueurs.
     *
     * @return Le tableau des joueurs.
     */
    public Joueur[] getJoueurs() {
        return joueurs;
    }

    /**
     * Retourne le joueur courant.
     *
     * @return Le joueur courant.
     */
    public Joueur getJoueurCourant() {
        return joueurCourant;
    }

    /**
     * Modifie le joueur courant.
     *
     * @param joueurCourant Le joueur courant.
     */
    public void setJoueurCourant(Joueur joueurCourant) {
        this.joueurCourant = joueurCourant;
    }

    /**
     * Retourne l'état de la partie (finie ou non).
     *
     * @return true si la partie est finie, sinon false.
     */
    public boolean isPartieFinie() {
        return partieFinie;
    }

    /**
     * Marque la partie comme terminée.
     *
     * @param partieFinie true si la partie est finie, sinon false.
     */
    public void setPartieFinie(boolean partieFinie) {
        this.partieFinie = partieFinie;
    }

    /**
     * Retourne si la partie s'est terminée par abandon.
     *
     * @return true si la partie s'est terminée par abandon, sinon false.
     */
    public boolean isParAbandon() {
        return parAbandon;
    }

    /**
     * Marque la partie comme terminée par abandon.
     *
     * @param parAbandon true si la partie s'est terminée par abandon, sinon false.
     */
    public void setParAbandon(boolean parAbandon) {
        this.parAbandon = parAbandon;
    }

    /**
     * Retourne le joueur gagnant.
     *
     * @return Le joueur gagnant ou null s'il n'y a pas de gagnant.
     */
    public Joueur getGagnant() {
        return gagnant;
    }

    /**
     * Modifie le joueur gagnant.
     *
     * @param gagnant Le joueur gagnant.
     */
    public void setGagnant(Joueur gagnant) {
        this.gagnant = gagnant;
    }

    /**
     * Change le joueur courant pour le joueur suivant.
     */
    public void changerJoueur() {
        this.joueurCourant = (joueurCourant == joueurs[0]) ? joueurs[1] : joueurs[0];
    }

    /**
     * Retourne une représentation textuelle de l'état de la partie.
     *
     * @return Une chaîne décrivant la partie.
     */
    @Override
    public String toString() {
        StringBuilder etat = new StringBuilder("Grille de jeu :\n" + grille.toString());
        etat.append("\nJoueur courant : ").append(joueurCourant.getCouleur());

        if (partieFinie) {
            if (gagnant != null) {
                etat.append("\nLe gagnant est : ").append(gagnant.getCouleur());
            } else if (parAbandon) {
                etat.append("\nPartie terminée par abandon.");
            } else {
                etat.append("\nMatch nul.");
            }
        }

        return etat.toString();
    }
}