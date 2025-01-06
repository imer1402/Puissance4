package Business;

public class Partie {
    private Grille grille;
    private Joueur[] joueurs;
    private Joueur joueurCourant;
    private boolean partieFinie;
    private Joueur gagnant;
    private boolean parAbandon;

    public Partie(Joueur joueur1, Joueur joueur2) {
        this.grille = new Grille();
        this.joueurs = new Joueur[]{joueur1, joueur2};
        this.joueurCourant = joueur1;
        this.partieFinie = false;
    }

    public Grille getGrille() {
        return grille;
    }

    public Joueur getJoueurCourant() {
        return joueurCourant;
    }

    public Joueur getGagnant() {
        return gagnant;
    }

    public void jouer(int numColonne) {
        if (partieFinie) return;

        Jeton jeton = new Jeton(joueurCourant.getNom());
        int ligne = grille.insererJeton(jeton, numColonne);
        if (ligne != -1) {
            if (grille.alignementRealise(new Position(ligne, numColonne))) {
                partieFinie = true;
                gagnant = joueurCourant;
            } else {
                joueurCourant = (joueurCourant == joueurs[0]) ? joueurs[1] : joueurs[0];
            }
        }
    }

    public void abandonner() {
        partieFinie = true;
        parAbandon = true;
    }

    public boolean isPartieFinie() {
        return partieFinie;
    }
}

