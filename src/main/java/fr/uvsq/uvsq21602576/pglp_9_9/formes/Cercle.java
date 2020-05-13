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
     * @param centre Centre du cercle
     * @param r Rayon du cercle
     */
    public Cercle(final Point centre, final int r) {
        super(centre);
        this.rayon = r;
    }

    /**
     * Retourne le haché correspondant.
     * @return Haché
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + rayon;
        return result;
    }

    /**
     * Teste si les deux Cercles sont égaux.
     * Deux Cercles sont égaux s'ils ont le même centre et le meme rayon.
     * @param obj Cercle à comparer
     * @return true si égaux, false sinon
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
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
     * Sous la forme 'Cercle(centre=,rayon=);
     * @return Représentation textuelle
     */
    @Override
    public String toString() {
        return "Cercle(centre=" + super.getPointReference().toString()
                + ",rayon=" + this.rayon + ")";
    }
}
