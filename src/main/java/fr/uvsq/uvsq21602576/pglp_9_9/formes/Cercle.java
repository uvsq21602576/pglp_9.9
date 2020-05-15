package fr.uvsq.uvsq21602576.pglp_9_9.formes;

/**
 * Représente un cercle,
 * Une Forme avec un rayon.
 * @author Flora
 */
public class Cercle extends Forme {
    /** Rayon du cercle. */
    private int rayon;

    /**
     * Constructeur.
     * Crée un cercle de centre centre et de rayon r.
     * @param nom Nom du cercle
     * @param centre Centre du cercle
     * @param r Rayon du cercle
     */
    public Cercle(final String nom, final Point centre, final int r) {
        super(nom, centre);
        this.rayon = r;
    }

    /**
     * Retourne le rayon du Cercle.
     * @return Rayon du cercle
     */
    public int getRayon() {
        return this.rayon;
    }

    /**
     * Retourne le haché correspondant.
     * @return Haché
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + rayon;
        return result;
    }

    /**
     * Teste si les deux Cercles sont égaux.
     * Deux Cercles sont égaux s'ils ont le même centre, le meme rayon et le
     * meme nom.
     * @param obj Cercle à comparer
     * @return true si égaux, false sinon
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof Cercle)) {
            return false;
        }
        Cercle other = (Cercle) obj;
        if (rayon != other.rayon) {
            return false;
        }
        return true;
    }

    /**
     * Retourne une représentation textuelle.
     * Sous la forme 'Cercle(centre=?,rayon=?);
     * @return Représentation textuelle
     */
    @Override
    public String toString() {
        return super.getNom() + " : Cercle(centre="
                + super.getPointReference().toString() + ",rayon=" + this.rayon
                + ")";
    }

    /**
     * Retourne une représentation textuelle.
     * Avec des tabulations pour la profondeur.
     * @param profondeur Profondeur dans l'arbre de Composant
     * @return Représentation textuelle
     */
    @Override
    public String toString(final int profondeur) {
        String s = "";
        for (int i = 0; i < profondeur; i++) {
            s = s.concat("    ");
        }
        s = s.concat("|-");
        return s.concat(this.toString());
    }
}
