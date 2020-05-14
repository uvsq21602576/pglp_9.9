package fr.uvsq.uvsq21602576.pglp_9_9.formes;

/**
 * Représente une forme.
 * Caractérisée par un point de reférence.
 * Classe Abstraite.
 * @author Flora
 */
public abstract class Forme implements ComposantDessin {
    /** Point de référence. */
    private Point pointReference;
    /** Nom de la variable. */
    private String nom;

    /**
     * Constructeur.
     * Initialise le point de reference de la forme, et son nom.
     * @param n Nom de la forme
     * @param p Point de référence
     */
    protected Forme(final String n, final Point p) {
        this.nom = n;
        this.pointReference = p;
    }

    /**
     * Déplace la forme.
     * Et donc son point de référence.
     * @param dP Vecteur de déplacement.
     */
    public void deplace(final Point dP) {
        this.pointReference.deplace(dP);
    }

    /**
     * Renvoie une représentation textuelle de la forme.
     * @return Représentation textuelle
     */
    @Override
    public abstract String toString();

    /**
     * Retourne une copie du point de référence.
     * @return Point de référence
     */
    public Point getPointReference() {
        return pointReference.copie();
    }

    /**
     * Retourne le nom de la forme.
     * @return Nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne le haché correspondant.
     * @return Haché
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nom == null) ? 0 : nom.hashCode());
        result = prime * result
                + ((pointReference == null) ? 0 : pointReference.hashCode());
        return result;
    }

    /**
     * Teste si les deux Formes sont égales.
     * Deux Formes sont égales si elles ont le meme point de référence, et le
     * meme nom.
     * @param obj Forme à comparer
     * @return true si égaux, false sinon
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Forme)) {
            return false;
        }
        Forme other = (Forme) obj;
        if (nom == null) {
            if (other.nom != null) {
                return false;
            }
        } else if (!nom.equals(other.nom)) {
            return false;
        }
        if (pointReference == null) {
            if (other.pointReference != null) {
                return false;
            }
        } else if (!pointReference.equals(other.pointReference)) {
            return false;
        }
        return true;
    }
}
