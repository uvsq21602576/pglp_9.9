package fr.uvsq.uvsq21602576.pglp_9_9.formes;

/**
 * Représente un carré.
 * Rectangle avec coté de meme longueur.
 * @author Flora
 */
public class Carre extends Rectangle {

    /**
     * Constructeur.
     * Crée un carré a partir d'un point, d'un nom, et de sa longueur.
     * @param nom Nom de la forme
     * @param hg Point haut gauche
     * @param longueur Longueur de coté
     */
    public Carre(final String nom, final Point hg, final int longueur) {
        super(nom, hg, new Point(hg.getX() + longueur, hg.getY() + longueur));
    }

    /**
     * Retourne le haché correspondant.
     * @return Haché
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Teste si les deux Carrés sont égaux.
     * Deux Carrés sont égaux s'ils ont les memes point hautgauche, nom, et
     * longueur.
     * @param obj Carré à comparer
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
        if (!(obj instanceof Carre)) {
            return false;
        }
        return true;
    }

    /**
     * Retourne une représentation textuelle.
     * Sous la forme "Carré(HautGauche=?,longueur=?)"
     * @return Représentation textuelle
     */
    @Override
    public String toString() {
        return super.getNom() + " : Carré(HautGauche="
                + super.getPointReference().toString() + ",longueur="
                + super.getLongueur() + ")";
    }
}
