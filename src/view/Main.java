package view;

public class MainView {
    public static void main(String[] args) {
        Joueur joueur1 = new Joueur(Couleur.JAUNE);
        Joueur joueur2 = new Joueur(Couleur.ROUGE);

        Partie partie = new Partie(joueur1, joueur2);
        Puissance4 puissance4 = new Puissance4(partie);

        // Exécution d'une partie fictive
        puissance4.jouer(3);
        puissance4.jouer(4);
        puissance4.jouer(3);
        puissance4.jouer(4);
        puissance4.jouer(3);
        puissance4.jouer(4);

        System.out.println("Partie terminée : " + puissance4.gameIsOver());
    }
}