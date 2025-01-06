package Business;

public final class Joueur {
    private final Couleur nom; // Champ final pour garantir l'immuabilité

    // Constructeur pour initialiser la propriété
    public Joueur(Couleur nom) {
        if (nom == null) {
            throw new IllegalArgumentException("Le nom du joueur (Couleur) ne peut pas être null.");
        }
        this.nom = nom;
    }

    // Getter pour récupérer la valeur du champ
    public Couleur getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return "Joueur{" +
                "nom=" + nom +
                '}';
    }
}
