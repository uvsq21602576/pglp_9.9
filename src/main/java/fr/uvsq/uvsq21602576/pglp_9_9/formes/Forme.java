package fr.uvsq.uvsq21602576.pglp_9_9.formes;

/**
 * Représente une forme.
 * Caractérisée par un point de reférence.
 * Classe Abstraite.
 * @author Flora
 */
public abstract class Forme implements Deplacable {
    /** Point de référence. */
    private Point pointReference;

    /**
     * Constructeur.
     * Initialise le point de reference de la forme.
     * @param p Point de référence
     */
    protected Forme(final Point p) {
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
     * Retorune le point de référence
     * @return Point de référence
     */
    protected Point getPointReference() {
        return pointReference;
    }
}
