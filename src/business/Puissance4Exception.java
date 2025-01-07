package business;

/**
 * La classe Puissance4Exception représente une exception personnalisée
 * pour le jeu Puissance 4. Elle est utilisée pour signaler des erreurs
 * spécifiques liées à la logique du jeu, telles que des colonnes pleines
 * ou des erreurs spécifiques au mécanisme de jeu.
 */
public class Puissance4Exception extends Exception {

    /**
     * Le numéro de colonne ou autre contexte lié à l'exception.
     */
    private final int colonne;

    /**
     * Constructeur par défaut pour Puissance4Exception.
     */
    public Puissance4Exception() {
        super();
        this.colonne = -1; // Valeur par défaut pour indiquer qu'il n'y a pas de contexte de colonne
    }

    /**
     * Constructeur avec un message d'erreur spécifique.
     * @param message Le message d'erreur associé à l'exception
     */
    public Puissance4Exception(String message) {
        super(message);
        this.colonne = -1;
    }

    /**
     * Constructeur avec un message et un numéro de colonne spécifique.
     * @param message Le message d'erreur associé à l'exception
     * @param colonne Le numéro de colonne concerné par l'erreur
     */
    public Puissance4Exception(String message, int colonne) {
        super(message);
        this.colonne = colonne;
    }

    /**
     * Constructeur avec une cause spécifique.
     * @param cause La cause sous-jacente de l'exception
     */
    public Puissance4Exception(Throwable cause) {
        super(cause);
        this.colonne = -1;
    }

    /**
     * Constructeur avec un message, une cause et un numéro de colonne spécifique.
     * @param message Le message d'erreur associé à l'exception
     * @param cause La cause sous-jacente de l'exception
     * @param colonne Le numéro de colonne concerné par l'erreur
     */
    public Puissance4Exception(String message, Throwable cause, int colonne) {
        super(message, cause);
        this.colonne = colonne;
    }

    /**
     * Retourne le numéro de colonne associé à l'exception.
     * @return Le numéro de colonne, ou -1 si non spécifié
     */
    public int getColonne() {
        return colonne;
    }
}