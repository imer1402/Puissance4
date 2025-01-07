package view;
import business.Puissance4;
import business.Joueur;
import java.util.Scanner;

/**
 * Classe principale de l'application Puissance 4.
 * Cette classe gère l'affichage et les interactions avec l'utilisateur.
 */
public class MainView {

    private final Puissance4 puissance4;

    /**
     * Constructeur qui initialise le jeu Puissance 4.
     */
    public MainView() {
        this.puissance4 = new Puissance4();
    }

    /**
     * Point d'entrée de l'application.
     *
     * @param args Arguments de la ligne de commande (non utilisés).
     */
    public static void main(String[] args) {
        MainView mainView = new MainView();
        mainView.run();
    }

    /**
     * Gère la boucle principale du jeu.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (!puissance4.gameIsOver()) {
            display();
            Joueur joueurCourant = puissance4.getPartie().getJoueurCourant();
            System.out.println("C'est au tour de " + joueurCourant.getCouleur() + ".");
            System.out.println("Entrez le numéro de colonne pour jouer ou 'a' pour abandonner :");

            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("a")) {
                puissance4.abandonner();
                break;
            }

            try {
                int colonne = Integer.parseInt(input);
                puissance4.jouer(colonne);
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un numéro de colonne ou 'a' pour abandonner.");
            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }

        displayFinalResult();
        scanner.close();
    }

    /**
     * Affiche la partie en cours.
     */
    public void display() {
        System.out.println("\n\n" + puissance4.getPartie().getGrille());
    }

    /**
     * Affiche le résultat final de la partie.
     */
    private void displayFinalResult() {
        if (puissance4.getPartie().isParAbandon()) {
            System.out.println("La partie s'est terminée par abandon.");
        }

        Joueur gagnant = puissance4.getPartie().getGagnant();
        if (gagnant != null) {
            System.out.println("Le gagnant est : " + gagnant.getCouleur() + " !");
        } else {
            System.out.println("Match nul !");
        }

        System.out.println("Merci d'avoir joué !");
    }
}
