package business;

public class Jeton {
    private Couleur couleur;

    public Jeton(Couleur couleur) {
        this.couleur = couleur;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    @Override
    public String toString() {
        return "Jeton{" + "couleur=" + couleur + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jeton jeton = (Jeton) o;
        return couleur == jeton.couleur;
    }
}

